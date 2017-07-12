package kim.refdata

class BootStrap {

  def grailsApplication

  def init = { servletContext ->

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

    if ( grailsApplication.config.refdataConfig ) {
      log.debug("Found a refdata config setting");
      java.net.URL config_url = new java.net.URL(grailsApplication.config.refdataConfig)
      if ( "file".equals(config_url.getProtocol()) ) {
        java.io.File config_file = new File(resourceUrl.toURI())
        if ( config_file.isDirectory() ) {
          log.debug("Passed a directory as config location");
        }
        else {
          log.debug("Processing config file");
        }
      }
      else {
        log.warn("This module currently only supports file:// protocol for refdata configuration");
      }
    }


  }

  def destroy = {
  }
}
