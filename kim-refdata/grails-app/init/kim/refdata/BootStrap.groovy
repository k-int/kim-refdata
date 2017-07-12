package kim.refdata

import groovy.json.JsonSlurper

class BootStrap {

  def grailsApplication

  def init = { servletContext ->
    log.debug("kim-refdata::bootstrap::init ${grailsApplication.config.wibble} ${System.getProperty("user.dir")}");

    // This app will look for a JSON file which defines the default set of global
    // refdata. This file is given as a URL, if none is supplied the app will attempt to
    // load the list from 
    // The file should be structured as follows
    // {
    //   category-shortcode: {
    //     value-shortcode: {
    //       defaultLabel: 'defaultLabel',
    //       sortKey: 'optionalSortKey',
    //       style: 'optionalStyle',
    //       status: 'GLOBAL_ROW_STATUS:ACTIVE',
    //     }
    //   }

    if ( grailsApplication.config.refdata.cfg ) {
      log.debug("Found a refdata config setting : ${grailsApplication.config.refdata?.cfg}");
      loadRefdataConfig(grailsApplication.config.refdata?.cfg)
    }
    else {
      log.debug("No defdata refdata cfg setting");
    }


  }

  def destroy = {
  }

  private def loadRefdataConfig(config_url_str) {
    try {
      java.net.URL config_url = new java.net.URL(config_url_str)
      // config_url should be a json file, parse it using JsonSlurper
      def config = new JsonSlurper().parse(config_url)
      log.debug("Process config file");
      config.categories.each { catShortcode, catValues ->
        updateCategory(catShortcode,catValues);
      }
    }
    catch ( Exception e ) {
      log.error("problem processing refdata config file ${config_url_str}",e);
    }
  }

  private def updateCategory(shortcode, values) {
    log.debug("updateCategory(${shortcode},...)");
    values.each { value_shortcode, value_definition ->
      if ( value_definition instanceof Map ) {
        log.debug("  Map defn : ${value_shortcode} -> ${value_definition.defaultLabel.toString()}")
        // def rdv = RefdataValue.lookupOrCreateGlobal(shortcode, value_shortcode, value_definition.defaultLabel.toString());
      }
      else {
        log.debug("  Simple defn : ${value_shortcode} -> ${value_definition.toString()}")
        // def rdv = RefdataValue.lookupOrCreateGlobal(shortcode, value_shortcode, value_definition.toString());
      }
    }
  }
}
