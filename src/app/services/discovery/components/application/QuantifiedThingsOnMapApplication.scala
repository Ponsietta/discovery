package services.discovery.components.application

class QuantifiedThingsOnMapApplication extends SimpleApplication {

    override protected val prefixes: String =
        """
          |PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>
          |PREFIX lpviz: <http://visualization.linkedpipes.com/ontology/>
          |PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
          |PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
        """.stripMargin

    override protected val whereClause: String =
        """
          | ?thing lpviz:hasAbstraction ?place .
          |
          |  ?place geo:location ?geo .
          |
          |  OPTIONAL {
          |    ?place rdfs:label ?abstractionLabel .
          |  }
          |
          |  ?geo a geo:SpatialThing ;
          |    geo:long ?long ;
          |    geo:lat ?lat .
          |
          |  ?thing lpviz:hasAbstraction ?quantity .
          |
          |  ?quantity rdf:value ?quantityValue .
          |
          |  OPTIONAL {
          |    ?quantity lpviz:unit ?quantityUnit .
          |  }
          |
          |  OPTIONAL {
          |    ?quantity rdfs:label ?quantityAbstractionLabel .
          |  }
        """.stripMargin
}