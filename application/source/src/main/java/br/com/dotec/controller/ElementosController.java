package br.com.dotec.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.dotec.infra.cache.Cache;
import br.com.dotec.infra.file.File;
import br.com.dotec.infra.interceptor.Restrito;
import br.com.dotec.infra.interceptor.UserInfo;
import br.com.dotec.model.Caixa;
import br.com.dotec.model.Cliente;
import br.com.dotec.model.Documento;
import br.com.dotec.model.Elemento;
import br.com.dotec.model.Envelope;
import br.com.dotec.model.Movimentacao;
import br.com.dotec.model.Pasta;
import br.com.dotec.model.StatusDaMovimentacao;
import br.com.dotec.model.TipoDeElemento;
import br.com.dotec.model.TipoDeMovimentacao;
import br.com.dotec.model.TipoDeUsuario;
import br.com.dotec.persistence.dao.CaixaDAO;
import br.com.dotec.persistence.dao.ClienteDAO;
import br.com.dotec.persistence.dao.DocumentoDao;
import br.com.dotec.persistence.dao.ElementoDAO;
import br.com.dotec.persistence.dao.EnvelopeDAO;
import br.com.dotec.persistence.dao.MovimentacaoDao;
import br.com.dotec.persistence.dao.PastaDAO;
import br.com.dotec.persistence.dao.TabelaDePrecoDAO;
import br.com.dotec.util.node.Attribute;
import br.com.dotec.util.node.Data;
import br.com.dotec.util.node.Node;
import br.com.dotec.util.node.NodeType;

@Resource
public class ElementosController {
	private final UserInfo userInfo;
	private final Cache cache;
	private final Result result;
	private final Validator validator;
	private final ElementoDAO elementoDAO;
	private final ClienteDAO clienteDAO;
	private final CaixaDAO caixaDAO;
	private final DocumentoDao documentoDao;
	private final PastaDAO pastaDAO;
	private final EnvelopeDAO envelopeDAO;
	private final MovimentacaoDao movimentacaoDao;
	private final TabelaDePrecoDAO tabelaDePrecoDAO;
	private final File file;

	public ElementosController(UserInfo userInfo, Cache cache, Result result,
			Validator validator, ElementoDAO elementoDAO,
			ClienteDAO clienteDAO, CaixaDAO caixaDAO,
			DocumentoDao documentoDao, PastaDAO pastaDAO,
			EnvelopeDAO envelopeDAO, MovimentacaoDao movimentacaoDao,
			TabelaDePrecoDAO tabelaDePrecoDAO, File file) {
		super();
		this.userInfo = userInfo;
		this.cache = cache;
		this.result = result;
		this.validator = validator;
		this.elementoDAO = elementoDAO;
		this.clienteDAO = clienteDAO;
		this.caixaDAO = caixaDAO;
		this.documentoDao = documentoDao;
		this.pastaDAO = pastaDAO;
		this.envelopeDAO = envelopeDAO;
		this.movimentacaoDao = movimentacaoDao;
		this.tabelaDePrecoDAO = tabelaDePrecoDAO;
		this.file = file;
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void lista() {
		result.include(userInfo);
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void nodesJson() {
		List<Node> nodes = new ArrayList<Node>();
		Cliente cliente = userInfo.getCliente();
		if (cache.getNodes(cliente) == null) {
			List<Elemento> elementos = clienteDAO.carrega(userInfo.getUser()).getElementos(false);
			nodes = this.getNodes(elementos, null);
			cache.setNodes(cliente, nodes);
		} else {
			nodes = cache.getNodes(cliente);
		}
		result.use(Results.json()).withoutRoot().from(nodes).include("data")
				.include("attr").include("children").recursive().serialize();
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void listaJson() {
		List<Elemento> elementos = clienteDAO.carrega(userInfo.getUser())
				.getElementos();
		result.use(Results.json()).from(elementos).serialize();
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void formularioPasta(Pasta pasta) {

	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void formularioDocumento(Documento documento) {

	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void formularioCaixa(Caixa caixa) {

	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void adicionaPasta(Pasta pasta, Long elementoPaiId) {
		pasta.setCriadoPor(userInfo.getUser());
		if (elementoPaiId != null) {
			Elemento elementoPai = elementoDAO.carrega(elementoPaiId);
			pasta.setElementoPai(elementoPai);
		}
		validator.validate(pasta);
		validator.onErrorUsePageOf(this).formularioPasta(pasta);
		Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId());
		cliente.getElementos().add(pasta);
		clienteDAO.merge(cliente);
		cache.limparCache(cliente);
		String message = "Pasta adicionada com sucesso";
		result.use(Results.json()).withoutRoot().from(message).serialize();
	}

	//@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void adicionaEnvelope(Envelope envelope, Long elementoPaiId) {
		envelope.setCriadoPor(userInfo.getUser());
		if (elementoPaiId != null) {
			Elemento elementoPai = elementoDAO.carrega(elementoPaiId);
			envelope.setElementoPai(elementoPai);
			envelope.setStatusDeLocalizacao(elementoPai.getStatusDeLocalizacao());
		}
		validator.validate(envelope);
		Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId());
		cliente.getElementos().add(envelope);
		clienteDAO.merge(cliente);
		cache.limparCache(cliente);
		String message = "Envelope adicionado com sucesso";
		result.use(Results.json()).withoutRoot().from(message).serialize();
	}

	// @Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void adicionaDocumento(Documento documento, Long elementoPaiId) {
		documento.setCriadoPor(userInfo.getUser());

		UploadedFile uploadedFile = userInfo.getUploadedFile();

		if (uploadedFile != null) {
			documento.setFile(file.salva(uploadedFile));
			userInfo.setUploadedFile(null);
		}

		if (elementoPaiId != null) {
			Elemento elementoPai = elementoDAO.carrega(elementoPaiId);
			documento.setElementoPai(elementoPai);
		}

		Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId());
		cliente.getElementos().add(documento);
		clienteDAO.merge(cliente);

		cache.limparCache(cliente);
		String message = "Documento adicionado com sucesso";
		result.use(Results.json()).withoutRoot().from(message).serialize();

	}

	// @Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void adicionaCaixa(Caixa caixa, Long elementoPaiId, Long quantidade) {

		verificaSeClientePodeFazerSolicitacoes();

		String name = caixa.getNome();
		for (int i = 0; i < quantidade; i++) {
			if (quantidade > 1 && i != 0) {
				caixa.setNome(name + " " + i);
			}

			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setCriadaPor(userInfo.getUser());
			movimentacao.setStatusDaMovimentacao(StatusDaMovimentacao.PENDENTE);
			movimentacao.setTipoDeMovimentacao(TipoDeMovimentacao.NOVA_CAIXA);

			if (tabelaDePrecoDAO.getValor("Nova Caixa") != null)
				movimentacao.setValor(tabelaDePrecoDAO.getValor("Nova Caixa")
						.getPrice());

			caixa.setCriadoPor(userInfo.getUser());

			if (elementoPaiId != null) {
				Elemento elementoPai = elementoDAO.carrega(elementoPaiId);
				caixa.setElementoPai(elementoPai);
			}

			Cliente cliente = clienteDAO.carrega(userInfo.getCliente().getId());

			caixa.getMovimentacoes().add(movimentacao);

			validator.validate(caixa);
			validator.onErrorUsePageOf(this).formularioCaixa(caixa);

			cliente.getElementos().add(caixa);
			clienteDAO.merge(cliente);
			caixa.getMovimentacoes().clear();

		}

		cache.limparCache(userInfo.getCliente());
		String message = "Caixa solicitada com sucesso! Aguarde o envio da DOTEC para inserir documentos.";
		result.use(Results.json()).withoutRoot().from(message).serialize();
	}

	// @Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void devolverElemento(Long id) {
		verificaSeClientePodeFazerSolicitacoes();
		Elemento elemento = elementoDAO.carrega(id);
		Movimentacao ultimaMovimentacao = elementoDAO.getUltimaMovimentacao(elemento);
		String tipoDeElemento = elemento.getTipoDeElemento().getNome();

		if (ultimaMovimentacao != null && ultimaMovimentacao.getStatusDaMovimentacao() == StatusDaMovimentacao.PENDENTE) {
			throw new RuntimeException(
					tipoDeElemento + " ainda possui a última solicitação pendente!");
		}

		Movimentacao movimentacaoDeDevolucao = new Movimentacao();
		movimentacaoDeDevolucao.setCriadaPor(userInfo.getUser());
		movimentacaoDeDevolucao
				.setStatusDaMovimentacao(StatusDaMovimentacao.PENDENTE);
		movimentacaoDeDevolucao
				.setTipoDeMovimentacao(TipoDeMovimentacao.DEVOLUCAO);

		elemento.getMovimentacoes().add(movimentacaoDeDevolucao);
		elementoDAO.merge(elemento);
		cache.limparCache(userInfo.getCliente());
		result.use(Results.json())
				.withoutRoot()
				.from("Solicitação de devolução enviada com sucesso, aguarde a presença da Dotec para buscar o(a) "+ tipoDeElemento +".")
				.serialize();
	}

	public void solicitarElemento(Long id) {
		verificaSeClientePodeFazerSolicitacoes();
		Elemento elemento = elementoDAO.carrega(id);
		Movimentacao ultimaMovimentacao = elementoDAO.getUltimaMovimentacao(elemento);
		String tipoDeElemento = elemento.getTipoDeElemento().getNome();
		
		
		if (ultimaMovimentacao != null && ultimaMovimentacao.getStatusDaMovimentacao() == StatusDaMovimentacao.PENDENTE) {
			throw new RuntimeException(
					tipoDeElemento + " ainda possui a última solicitação pendente!");
		}

		Movimentacao movimentacaoDeDevolucao = new Movimentacao();
		movimentacaoDeDevolucao.setCriadaPor(userInfo.getUser());
		movimentacaoDeDevolucao.setStatusDaMovimentacao(StatusDaMovimentacao.PENDENTE);
		movimentacaoDeDevolucao.setTipoDeMovimentacao(TipoDeMovimentacao.SOLICITACAO);
		elemento.getMovimentacoes().add(movimentacaoDeDevolucao);
					
		elementoDAO.merge(elemento);
		cache.limparCache(userInfo.getCliente());
		result.use(Results.json())
				.withoutRoot()
				.from("Solicitação do " + tipoDeElemento +  " enviada com sucesso, aguarde a presença da Dotec.")
				.serialize();
	}
	
	

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void cancelarMovimentacaoPendente(Long id) {
		Elemento elemento = elementoDAO.carrega(id);
		Movimentacao ultimaMovimentacao = elementoDAO.getUltimaMovimentacao(elemento);
		String tipoDeElemento = elemento.getTipoDeElemento().getNome();
		
		if (ultimaMovimentacao.getStatusDaMovimentacao() != StatusDaMovimentacao.PENDENTE) {
			throw new RuntimeException(
					tipoDeElemento + " não possui movimentação pendente!");
		}

		if (ultimaMovimentacao.getTipoDeMovimentacao() == TipoDeMovimentacao.NOVA_CAIXA) {
			Cliente cliente = clienteDAO.carrega(userInfo.getUser());
			cliente.getElementos().remove(elemento);
			clienteDAO.atualiza(cliente);
			ultimaMovimentacao.setCriadaPor(null);
			elemento.setElementoPai(null);
			elementoDAO.remove(elemento);
		} else {
			ultimaMovimentacao
					.setStatusDaMovimentacao(StatusDaMovimentacao.CANCELADA);
			movimentacaoDao.altera(ultimaMovimentacao);
		}

		cache.limparCache(userInfo.getCliente());
		result.use(Results.json()).withoutRoot()
				.from("Movimentação cancelada com sucesso").serialize();
	}

	public List<Node> getNodes(List<Elemento> elementos, Elemento elementoRaiz) {
		List<Node> nodes = new ArrayList<Node>();
		for (Elemento elemento : elementos) {

			Long elementoPaiId = null;
			Long elementoRaizId = null;

			if (elemento.getElementoPai() != null) {
				elementoPaiId = elemento.getElementoPai().getId();
			}

			if (elementoRaiz != null) {
				elementoRaizId = elementoRaiz.getId();
			}

			if (elementoPaiId == elementoRaizId) {
				Attribute attribute = new Attribute();
				attribute.setId(elemento.getId());
				Attribute attributeAnode = new Attribute();
								
				if(elemento.getTipoDeElemento()==TipoDeElemento.CAIXA){
					attribute.setRel(NodeType.caixa);
				}
				if(elemento.getTipoDeElemento()==TipoDeElemento.PASTA){
					attribute.setRel(NodeType.pasta);
				}
				if(elemento.getTipoDeElemento()==TipoDeElemento.DOCUMENTO){
					attribute.setRel(NodeType.documento);
				}
				if(elemento.getTipoDeElemento()==TipoDeElemento.ENVELOPE){
					attribute.setRel(NodeType.envelope);
				}
							
				Movimentacao ultimaMovimentacao = elementoDAO.getUltimaMovimentacao(elemento);
				attribute.setStatusDaCaixa(elemento.getStatusDeLocalizacao());
				attribute.setElementoAscendenteComMovimentacaoPendente(elementoDAO.possuiElementoAscendenteComMovimentacaoPendente(elemento));
				attribute.setElementoDescendenteComMovimentacaoPendente(elementoDAO.possuiElementoDescendenteComMovimentacaoPendente(elemento));
								
				String informacaoSobreUltimaMovimentacao = "Não possui movimentações";
				if (ultimaMovimentacao != null) {
					attribute.setStatusDaMovimentacao(ultimaMovimentacao.getStatusDaMovimentacao());
					String tipoDeMovimentacao = ultimaMovimentacao.getTipoDeMovimentacao().getNome();
					String statusDaMovimentacao = ultimaMovimentacao.getStatusDaMovimentacao().getNome();
					informacaoSobreUltimaMovimentacao = tipoDeMovimentacao + ", status " + statusDaMovimentacao;
				}
							
				String statusDeLocalizacao = "";
				if (elemento.getStatusDeLocalizacao()!= null){
					statusDeLocalizacao = elemento.getStatusDeLocalizacao().getNome();
				}	 

				attributeAnode.setTitle("Id: " + elemento.getId()
						+ " \nDescrição: " + elemento.getDescricao()
						+ " \nLocalização: " + statusDeLocalizacao
						+ " \nÚltima movimentação: " 
						+ informacaoSobreUltimaMovimentacao);

				Node node = new Node();
				Data data = new Data();
				if (elemento.getTipoDeElemento() == TipoDeElemento.CAIXA) {
					data.setTitle("nº " + elemento.getId() + " : "
							+ elemento.getNome());
				} else {
					data.setTitle(elemento.getNome());
				}
				data.setAttr(attributeAnode);
				node.setData(data);
				node.setAttr(attribute);
				node.setChildren(this.getNodes(elementos, elemento));
				nodes.add(node);

			}

		}
		return nodes;
	}

	public void updateElementoPai(Long elementoId, Long elementoPaiId) {
		Elemento elemento = elementoDAO.carrega(elementoId);
		if (elementoPaiId == null) {
			elemento.setElementoPai(null);
		} else {
			Elemento elementoPai = elementoDAO.carrega(elementoPaiId);
			elemento.setElementoPai(elementoPai);
		}
		elementoDAO.atualiza(elemento);
		cache.limparCache(userInfo.getCliente());
		result.use(Results.json()).from(1, "success").serialize();
	}

	public void removerElemento(Long id) {
		Elemento elemento = elementoDAO.carrega(id);
		elemento.setExcluido(true);
		elementoDAO.merge(elemento);
		
		for (Elemento elementoDescendente : elementoDAO.getElementosDescendentes(elemento)) {
			elementoDescendente.setExcluido(true);
			elementoDAO.merge(elementoDescendente);
		}
		String mensagem = elemento.getTipoDeElemento().getNome() + " removido(a) com sucesso.";
		cache.limparCache(userInfo.getCliente());
		result.use(Results.json()).withoutRoot().from(mensagem).serialize();
	}

	public void removerDocumento(Long id) {
		Documento documento = documentoDao.carrega(id);
		documento.setElementoPai(null);
		Cliente cliente = clienteDAO.carrega(userInfo.getUser());
		cliente.getElementos().remove(documento);
		elementoDAO.remove(documento);
		String mensagem = "Documento removido com sucesso.";
		cache.limparCache(cliente);
		result.use(Results.json()).withoutRoot().from(mensagem).serialize();
	}

	public void removerPasta(Long id) {

		Pasta pasta = pastaDAO.carrega(id);
		if (elementoDAO.possuiCaixaDescendente(pasta))
			throw new RuntimeException(
					"Pastas que possuem caixas não podem ser removidas");
		pasta.setElementoPai(null);
		Cliente cliente = clienteDAO.carrega(userInfo.getUser());
		for (Elemento elementoDescendente : elementoDAO
				.getElementosDescendentes(pasta)) {
			cliente.getElementos().remove(elementoDescendente);
			elementoDAO.remove(elementoDescendente);
		}
		cliente.getElementos().remove(pasta);
		elementoDAO.remove(pasta);
		String mensagem = "Pasta removida com sucesso.";
		cache.limparCache(cliente);
		result.use(Results.json()).withoutRoot().from(mensagem).serialize();
	}

	public void removerEnvelope(Long id) {
		Envelope envelope = envelopeDAO.carrega(id);
		envelope.setElementoPai(null);
		Cliente cliente = clienteDAO.carrega(userInfo.getUser());
		for (Elemento elementoDescendente : elementoDAO.getElementosDescendentes(envelope)) {
			cliente.getElementos().remove(elementoDescendente);
			elementoDAO.remove(elementoDescendente);
		}
		cliente.getElementos().remove(envelope);
		elementoDAO.remove(envelope);
		String mensagem = "Envelope removido com sucesso.";
		cache.limparCache(cliente);
		result.use(Results.json()).withoutRoot().from(mensagem).serialize();
	}

	public List<Documento> carregaDocumento(Long id) {
		Elemento elemento = elementoDAO.carrega(id);

		List<Documento> listDoc = new ArrayList<Documento>();

		if (elemento.getTipoDeElemento().equals(TipoDeElemento.CAIXA) || elemento.getTipoDeElemento().equals(TipoDeElemento.ENVELOPE)) {
			List<Elemento> list = elementoDAO.getElementosFilhos(elemento);

			for (Elemento elemento2 : list) {
				if(elemento2.getTipoDeElemento()==TipoDeElemento.DOCUMENTO){
					Documento documento = documentoDao.carrega(elemento2.getId());
					listDoc.add(documento);
				}
			}

		}

		result.include("elemento", elemento);
		return listDoc;
	}

	@Restrito({ TipoDeUsuario.PRIMARIO, TipoDeUsuario.SECUNDARIO })
	public void renomear(Long elementoId, String elementoNome,
			String elementoDescricao) {
		Elemento elementoAux = elementoDAO.carrega(elementoId);
		String mensagem = "";
		if (elementoAux.getTipoDeElemento() == TipoDeElemento.CAIXA) {
			mensagem = "Caixa renomeada com sucesso";
		}
		if (elementoAux.getTipoDeElemento() == TipoDeElemento.PASTA) {
			mensagem = "Pasta renomeada com sucesso";
		}
		if (elementoAux.getTipoDeElemento() == TipoDeElemento.DOCUMENTO) {
			mensagem = "Documento renomeado com sucesso";
		}
		elementoAux.setNome(elementoNome);
		elementoAux.setDescricao(elementoDescricao);
		elementoDAO.merge(elementoAux);
		cache.limparCache(userInfo.getCliente());
		result.use(Results.json()).withoutRoot().from(mensagem).serialize();
	}

	public void verificaSeClientePodeFazerSolicitacoes() {
		Cliente cliente = clienteDAO.carrega(userInfo.getUser());

		if (!(cliente.isHabilitado())) {
			throw new RuntimeException(
					"Cliente só poderá fazer solicitações após a confirmação de pagamento");
		}

		if (cliente.isInativo()) {
			throw new RuntimeException(
					"Cliente inativado, favor entrar em contato com a Dotec para regularizar a sua situação!");
		}
	}

}
