package br.com.dotec;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GeraTabelas {
	public static void main(String[] args) {
		AnnotationConfiguration  cfg = new AnnotationConfiguration();
		//cfg.addAnnotatedClass(PessoaFisica.class);
		//cfg.addAnnotatedClass(Funcionario.class);
		//cfg.addAnnotatedClass(Endereco.class);
		//cfg.addAnnotatedClass(TipoDeLogradouro.class);
		//cfg.addAnnotatedClass(Proposta.class);
		//cfg.addAnnotatedClass(PerfilDoCliente.class);
		//cfg.addAnnotatedClass(Produto.class);
		SchemaExport se = new SchemaExport(cfg);
		se.create(true, true);
	}

}
