<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  version="1.0">


	<xsl:output omit-xml-declaration = "yes" />

	<xsl:template match="/">	            
        	<xsl:variable name="root" ><xsl:value-of select="/caixas/caixa[caixaPai='#']/id" /></xsl:variable>        	
			<xsl:for-each select="/caixas/caixa[caixaPai=$root]">
				<li><span class="folder"><xsl:value-of select="nome" /></span>
				<xsl:call-template name="recursive">					
					<xsl:with-param name="id"><xsl:value-of select="id"/></xsl:with-param>
				</xsl:call-template>
				
					<xsl:if test="documentos/documento">
						<ul>
							<xsl:for-each select="documentos/documento">
								<li><span class="file"><xsl:value-of select="nome"/></span></li>						
							</xsl:for-each>
						</ul>
					</xsl:if>						
				</li>				
			</xsl:for-each>		
	</xsl:template>
		
	<xsl:template name="recursive">			
		<xsl:param name="id"/>
		<xsl:variable name="childrenCount"><xsl:value-of select="count(/caixas/caixa[caixaPai = $id])" /></xsl:variable>
		
		<xsl:for-each select="/caixas/caixa[caixaPai=$id]">
			<xsl:if test="$childrenCount > 0">
				<ul>
					<li ><span class="folder"><xsl:value-of select="nome" /></span>														
						<xsl:call-template name="recursive">					
							<xsl:with-param name="id"><xsl:value-of select="id" /></xsl:with-param>					
						</xsl:call-template>
					
						<xsl:if test="documentos/documento">
							<ul>
								<xsl:for-each select="documentos/documento">									
									<li><span class="file"><xsl:value-of select="nome"/></span></li>						
								</xsl:for-each>
							</ul>
						</xsl:if>
					</li>								
				</ul>
			</xsl:if>			
		</xsl:for-each>				
	</xsl:template>
	
</xsl:stylesheet>
