import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;

import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;

@Component
public class CustomJSONSerialization extends XStreamJSONSerialization {

	public CustomJSONSerialization(HttpServletResponse response,
			TypeNameExtractor extractor, ProxyInitializer initializer,
			XStreamBuilder builder) {
		super(response, extractor, initializer, builder);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected XStream getXStream() {
		XStream xstream = super.getXStream();

		xstream.registerConverter(new CollectionConverter(xstream.getMapper()) {
			@Override
			@SuppressWarnings("rawtypes")
			public boolean canConvert(Class type) {
				return Collection.class.isAssignableFrom(type);
			}
		});

		return xstream;
	}

}
