# kim-refdata

A Folio module for managing shared collections of refdata in dependent FOLIO modules.

## Development

TO run this module as a stand-alone item, from the project dir (./kim-refdata) you should run

java -jar ./build/libs/kim-refdata-0.1.war --spring.config.location=file:../folio_globals.yaml

or you can run with grails run-app using

    THIS SEEMS NOT TO WORK AS EXPECTED::
    grails -Dspring.config.location=file:../folio_globals.yaml run-app

    BUT THIS IS OK::
    SPRING_CONFIG_LOCATION="file:../folio_globals.yaml" grails run-app


