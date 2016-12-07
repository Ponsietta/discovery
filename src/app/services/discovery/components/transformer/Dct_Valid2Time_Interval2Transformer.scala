package services.discovery.components.transformer

class Dct_Valid2Time_Interval2Transformer extends SparqlUpdateTransformer {

    protected override val prefixes =
        """
          |PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
          |PREFIX dct: <http://purl.org/dc/terms/>
          |PREFIX time: <http://www.w3.org/2006/time#>
          |PREFIX lpviz: <http://visualization.linkedpipes.com/ontology/>
        """.stripMargin

    protected override val deleteClause =
        """
          |?s dct:valid ?valid .
        """.stripMargin

    protected override val insertClause =
        """
          |?s lpviz:hasAbstraction ?abstraction .
          |
          |?abstraction
          |    a time:Interval ;
          |    time:hasBeginning ?abstractionBeginning ;
          |    time:hasEnd ?abstractionEnd ;
          |    rdfs:label ?abstractionLabel .
          |
          |?abstractionBeginning
          |    a time:Instant ;
          |    time:inXSDDateTime ?start .
          |
          |?abstractionEnd
          |    a time:Instant ;
          |    time:inXSDDateTime ?end .
        """.stripMargin

    protected override val whereClause =
        """
          |?s dct:valid ?valid .
          |
          |{
          |    ?s dct:valid ?valid .
          |    BIND(REPLACE(STR(?valid), ".*start=([0-9]{4}-[0-9]{2}-[0-9]{2}).*", "$1") AS ?start)
          |    FILTER(REGEX(?start, "^([0-9]{4}-[0-9]{2}-[0-9]{2})$"))
          |} UNION {
          |    ?s dct:valid ?valid .
          |    BIND(REPLACE(STR(?valid), ".*end=([0-9]{4}-[0-9]{2}-[0-9]{2}).*", "$1") AS ?end)
          |    FILTER(REGEX(?end, "^([0-9]{4}-[0-9]{2}-[0-9]{2})$"))
          |}
          |
          |OPTIONAL {
          |    ?s dct:title ?title .
          |    BIND(CONCAT("Validity of ", STR(?title)) AS ?abstractionLabel)
          |}
          |
          |BIND(IRI(CONCAT(STR(?s), "/abstraction/dct-valid2time-Interval-02")) AS ?abstraction)
          |BIND(IRI(CONCAT(STR(?s), "/abstraction/dct-valid2time-Interval-02/beginning")) AS ?abstractionBeginning)
          |BIND(IRI(CONCAT(STR(?s), "/abstraction/dct-valid2time-Interval-02/end")) AS ?abstractionEnd)
        """.stripMargin
}
