# Owl
[![Build Status](https://travis-ci.org/stevesoltys/owl.svg?branch=master)](https://travis-ci.org/stevesoltys/owl)  

Owl is a systems and network monitoring application written in Java using Spring.

# Configuration
By default, Owl will look for a JSON configuration file in your home directory: ```$HOME/.config/owl/config.json```.

Configuration is  currently limited and under development. An example configuration file can be seen below:

```
{
    "component_types": [
        {
            "identifier": "cpu_load",
            "classpath": "com.stevesoltys.owl.model.component.CPULoadComponent",
            "controller_classpath": "com.stevesoltys.owl.controller.component.CPULoadComponentController"
        }
    ],

    "agent": true
}
```