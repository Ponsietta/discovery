package services.discovery.components.transformer

class Dbpedia_PopulationTotal2Rdf_ValueTransformer extends SparqlUpdateTransformer {

    protected override val prefixes =
        """
          | PREFIX owl: <http://www.w3.org/2002/07/owl#>
          | PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
          | PREFIX dbo: <http://dbpedia.org/ontology/>
        """.stripMargin

    protected override val deleteClause =
        """
          |?town dbo:populationTotal ?population .
        """.stripMargin

    protected override val insertClause =
        """
          |?town lpviz:hasAbstraction ?abstraction .
          |
          |?abstraction rdf:value ?population ;
          |    rdfs:label ?abstractionLabel .
        """.stripMargin

    protected override val whereClause =
        """
          |?town dbo:populationTotal ?population .
          |
          |OPTIONAL {
          |    ?town rdfs:label ?label .
          |    BIND(CONCAT("Total population of ", STR(?label)) AS ?abstractionLabel)
          |}
          |
          |BIND(IRI(CONCAT(STR(?town), "/abstraction/dbpedia-populationTotal2rdf-value")) AS ?abstraction)
        """.stripMargin
}