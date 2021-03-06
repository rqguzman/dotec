/**
 * Copyright (c) 2009, Nathan Bubna
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 * This plugin exists to make it trivial to notify your users that
 * things are in progress.  The typical case is waiting for an
 * AJAX call to finish loading.  Just call:
 * 
 *   $.loading();
 *
 * to toggle a page-wide message on and off, or you can call:
 *
 *   $('#foo').loading()
 *
 * to do the same, but locate the message within a specific element(s).
 *
 * If you want to ensure that a call doesn't errantly toggle on when
 * you meant to toggle off (or vice versa), then put a boolean value
 * as your first argument.  true is on, false is off.
 *
 *   $.loading(false); // will only ever toggle off
 *   $.loading(true, {align: 'bottom-right'});  // will only ever toggle on
 *
 * If you want a loading message to automatically go on while your
 * AJAX stuff is happening (and off when done), there's a convenient option
 * to set that up properly for you. Just do:
 *
 *   $.loading({onAjax:true, text: 'Waiting...'});
 *
 * You can change any of the default options by altering the $.loading
 * properties (or sub-properties), like this:
 *
 *  $.loading.classname = 'loadMsg';
 *  $.loading.css.border = '1px solid #000';
 *  $.loading.working.time = 5000;
 *
 * All options can also be overridden per-call by passing in
 * an options object with the overriding properties, like so:
 *
 *  $.loading({ element:'#cancelButton', mask:true });
 *  $('#foo').loading(true, { img:'loading.gif', align:'center'});
 *
 * And if that isn't enough, this plugin supports the metadata plugin as a
 * way to specify options directly in your markup.
 *
 * Be sure to check out the provided demo for an easy overview of the most
 * commonly used options!! Of course, everything in this plugin is easy to
 * configure and/or override with those same techniques.
 *
 * To employ multiple pulse effects at once, just separate with spaces:
 *
 *  $.loading({ pulse: 'working fade', mask:true });
 *
 * Of particular note here is that it is easy to plug in additional
 * "pulse" effects.  Just add an object with a 'run' function to $.loading
 * under the desired effects name, like this:
 *
 *  $.loading.moveLeft = {
 *      time: 500,
 *      run: function(opts) {
 *          var self = this, box = opts.box;
 *          // save interval like this and it will be cleared for you
 *          // when this loading call is toggled off
 *          opts.moveLeft.interval = setInterval(function() {
 *              box.left += 1;
 *              self.animate(box);
 *          }, opts.moveLeft.time);
 *      }
 *  }
 *
 * then use it by doing something like:
 * 
 *  $.loading({ pulse: 'moveLeft', align:{top:0,left:0} });
 *
 * If you add an 'end' function to that same object, then the end function
 * will be called when the loading message is turned off.
 *
 * Speaking of turning things on and off, this plugin will trigger 'loadingStart'
 * and 'loadingEnd' events when loading is turned on and off, respectively.
 * The options will, of course, be available as a second argument to functions
 * that are bound to these events.  See the demo source for an example. In
 * future versions, this plugin itself may use those events, but for now they
 * are merely notifications.
 *
 * Contributions, bug reports and general feedback on this is welcome.
 *
 * @version 1.4
 * @name loading
 * @author Nathan Bubna
 */
;(function($) {

    // the main interface...
    var L = $.loading = function(show, opts) {
        return $('body').loading(show, opts, true);
    };
    $.fn.loading = function(show, opts, page) {
        opts = toOpts(show, opts);
        var base = page ? $.extend(true, {}, L, L.pageOptions) : L;
        return this.each(function() {
            var $el = $(this),               // support metadata
                o = $.extend(true, {}, base, $.metadata ? $el.metadata() : null, opts);
            if (typeof o.onAjax == "boolean") {
                L.setAjax.call($el, o);
            } else {
                L.toggle.call($el, o);
            }
        });
    };

    // position CSS for page opts //TODO: better support test...
    var fixed = { position: $.browser.msie ? 'absolute' : 'fixed' };

    // all that's extensible and configurable...
    $.extend(L, {
        version: "1.4",
        // commonly-used options
        align: 'top-left',
        pulse: 'working error',
        mask: false,
        img: null,
        element: null,
        text: 'Loading...',
        onAjax: undefined,
        // less commonly-used options
        classname: 'loading',
        imgClass: 'loading-img',
        elementClass: 'loading-element',
        maskClass: 'loading-mask',
        css: { position:'absolute', whiteSpace:'nowrap', zIndex:1001 },
        maskCss: { position:'absolute', opacity:.15, background:'#333',
                   zIndex:101, display:'block', cursor:'wait' },
        cloneEvents: true,
        pageOptions: { page:true, align:'top-center', css:fixed, maskCss:fixed },
        // rarely-used options
        html: '<div></div>',
        maskHtml: '<div></div>',
        maskedClass: 'loading-masked',
        maskEvents: 'mousedown mouseup keydown keypress',
        resizeEvents: 'resize',

        // pulse plugin properties
        working: {
            time: 10000,
            text: 'Still working...',
            run: function(opts) {
                var w = opts.working, self = this;
                w.timeout = setTimeout(function() {
                    self.height('auto').width('auto').text(opts.text = w.text);
                    opts.place.call(self, opts);
                }, w.time);
            }
        },
        error: {
            time: 100000,
            text: 'Task may have failed...',
            classname: 'loading-error',
            run: function(opts) {
                var e = opts.error, self = this;
                e.timeout = setTimeout(function() {
                    self.height('auto').width('auto').text(opts.text = e.text).addClass(e.classname);
                    opts.place.call(self, opts);
                }, e.time);
            }
        },
        fade: {
            time: 800,
            speed: 'slow',
            run: function(opts) {
                var f = opts.fade, s = f.speed, self = this;
                f.interval = setInterval(function() {
                    self.fadeOut(s).fadeIn(s);
                }, f.time);
            }
        },
        ellipsis: {
            time: 300,
            run: function(opts) {
                var e = opts.ellipsis, self = this;
                e.interval = setInterval(function() {
                    var et = self.text(), t = opts.text, i = dotIndex(t);
                    self.text((et.length - i) < 3 ? et + '.' : t.substring(0,i));
                }, e.time);
                function dotIndex(t) {
                    var x = t.indexOf('.');
                    return x < 0 ? t.length : x;
                }
            }
        },
        type: {
            time: 100,
            run: function(opts) {
                var t = opts.type, self = this;
                t.interval = setInterval(function() {
                    var e = self.text(), el = e.length, txt = opts.text;
                    self.text(el == txt.length ? txt.charAt(0) : txt.substring(0, el+1));
                }, t.time);
            }
        },

        // functions
        toggle: function(opts) {
            var old = this.data('loading');
            if (old) {
                if (opts.show !== true) old.off.call(this, old, opts);
            } else {
                if (opts.show !== false) opts.on.call(this, opts);
            }
        },
        setAjax: function(opts) {
            if (opts.onAjax) {
                var self = this, A = opts.ajax = { count: 0 };
                A.start = function(e) { if (A.count++ < 1) opts.on.call(self, opts); };
                A.stop =  function(e) { if (--A.count < 1) opts.off.call(self, opts, opts); };
                this.bind('ajaxStart.loading', A.start).bind('ajaxStop.loading', A.stop);
            } else {
                this.unbind('ajaxStart.loading').unbind('ajaxStop.loading');
            }
        },
        on: function(opts) {
            opts.parent = this;
            if (opts.mask) opts.mask = opts.createMask.call(this, opts);
            opts.display = opts.create.call(this, opts);
            if (opts.img) {
                opts.initImg.call(this, opts);
            } else if (opts.element) {
                opts.initElement.call(this, opts);
            } else {
                opts.init.call(this, opts);
            }
            this.trigger('loadingStart', [opts]);
        },
        initImg: function(opts) {
            var self = this;
            opts.img = $('<img src="'+opts.img+'"/>').bind('load', function() {
                opts.init.call(self, opts);
            });
            opts.display.addClass(opts.imgClass).append(opts.img);
        },
        initElement: function(opts) {
            opts.element = $(opts.element).clone(opts.cloneEvents).show();
            opts.display.addClass(opts.elementClass).append(opts.element);
            opts.init.call(this, opts);
        },
        init: function(opts) {
            opts.place.call(opts.display, opts);
            this.data('loading', opts);
            if (opts.pulse) opts.initPulse.call(this, opts);
        },
        initPulse: function(opts) {
            $.each(opts.pulse.split(' '), function() {
                opts[this].run.call(opts.display, opts);
            });
        },
        create: function(opts) {
            var el = $(opts.html).addClass(opts.classname).css(opts.css).appendTo(this);
            if (opts.text && !opts.img && !opts.element) el.text(opts.text);
            $(window).bind(opts.resizeEvents, opts.resizer = function() { opts.resize(opts); });
            return el;
        },
        resize: function(opts) {
            opts.parent.box = null;
            if (opts.mask) opts.mask.hide();
            opts.place.call(opts.display.hide(), opts);
            if (opts.mask) opts.mask.show().css(opts.parent.box);
        },
        createMask: function(opts) {
            var box = opts.measure.call(this.addClass(opts.maskedClass), opts);
            opts.handler = function(e) { return opts.maskHandler(e, opts); };
            $(document).bind(opts.maskEvents, opts.handler);
            return $(opts.maskHtml).addClass(opts.maskClass)
                .css(box).css(opts.maskCss).appendTo(this);
        },
        maskHandler: function(e, opts) {
            var $els = $(e.target).parents().andSelf();
            if ($els.filter('.'+opts.classname).length != 0) return true;
            return !opts.page && $els.filter('.'+opts.maskedClass).length == 0;
        },
        place: function(opts) {
            var box = opts.align, v = 'top', h = 'left';
            if (typeof box == "object") {
                box = $.extend(opts.calc.call(this, v, h, opts), box);
            } else {
                if (box != 'top-left') {
                    var s = box.split('-');
                    if (s.length == 1) {
                        v = h = s[0];
                    } else {
                        v = s[0]; h = s[1];
                    }
                }
                if (!this.hasClass(v)) this.addClass(v);
                if (!this.hasClass(h)) this.addClass(h);
                box = opts.calc.call(this, v, h, opts);
            }
            this.show().css(opts.box = box);
        },
        calc: function(v, h, opts) {
            var box = $.extend({}, opts.measure.call(opts.parent, opts)),
                H = $.boxModel ? this.height() : this.innerHeight(),
                W = $.boxModel ? this.width() : this.innerWidth();
            if (v != 'top') {
                var d = box.height - H;
                if (v == 'center') {
                    d /= 2;
                } else if (v != 'bottom') {
                    d = 0;
                } else if ($.boxModel) {
                    d -= css(this, 'paddingTop') + css(this, 'paddingBottom');
                }
                box.top += d;
            }
            if (h != 'left') {
                var d = box.width - W;
                if (h == 'center') {
                    d /= 2;
                } else if (h != 'right') {
                    d = 0;
                } else if ($.boxModel) {
                    d -= css(this, 'paddingLeft') + css(this, 'paddingRight');
                }
                box.left += d;
            }
            box.height = H;
            box.width = W;
            return box;
        },
        measure: function(opts) {
            return this.box || (this.box = opts.page ? opts.pageBox(opts) : opts.elementBox(this, opts));
        },
        elementBox: function(e, opts) {
            var box = e.position();
            box.top += css(e, 'marginTop');
            box.left += css(e, 'marginLeft');
            box.height = e.outerHeight();
            box.width = e.outerWidth();
            return box;
        },
        pageBox: function(opts) {
            var d = document, b = d.body, full = $.boxModel && opts.css.position != 'fixed',
                h = full ? $(d).height() : b.clientHeight,
                w = full ? $(d).width() : b.clientWidth;
            return { top:0, left: 0, height: h, width: w };
        },
        off: function(old, opts) {
            this.data('loading', null);
            if (old.pulse) old.stopPulse.call(this, old, opts);
            if (old.mask) old.stopMask.call(this, old, opts);
            $(window).unbind(old.resizeEvents, old.resizer);
            old.display.remove();
            old.parent.trigger('loadingEnd', [old]);
        },
        stopPulse: function(old, opts) {
            $.each(old.pulse.split(' '), function() {
                var p = old[this];
                if (p.end) p.end.call(opts.display, old, opts);
                if (p.interval) clearInterval(p.interval);
                if (p.timeout) clearTimeout(p.timeout);
            });
        },
        stopMask: function(old, opts) {
            this.removeClass(opts.maskedClass);
            $(document).unbind(old.maskEvents, old.handler);
            old.mask.remove();
        }
    });

    // deprecated API, estimated removal in 1.6...
    L.onAjax = function(opts) { $.loading($.extend({onAjax:true}, opts)); };

    // a few private functions...
    function toOpts(s, o) {
        if (o === undefined) {
            o = (typeof s == "boolean") ? { show: s } : s;
        } else {
            o.show = s;
        }
        // default pulse to off if doing an img
        if (o && (o.img || o.element) && !o.pulse) o.pulse = false;
        // if onAjax and they didn't specify show, default to false
        if (o && o.onAjax !== undefined && o.show === undefined) o.show = false;
        return o;
    }
    function css(el, prop) {
        var val = el.css(prop);
        return val == 'auto' ? 0 : parseFloat(val, 10);
    }
})(jQuery);
