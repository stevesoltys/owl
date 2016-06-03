# Owl
[![Build Status](https://travis-ci.org/stevesoltys/owl.svg?branch=master)](https://travis-ci.org/stevesoltys/owl)  

Owl is a systems and network monitoring application written in Java using Spring.

## Configuration
By default, Owl will look for a JSON configuration file in your home directory: ```$HOME/.config/owl/config.json```.

An example configuration file can be seen below:

```json
{
    "component_types": [
        {
            "identifier": "cpu_load",
            "classpath": "com.stevesoltys.owl.model.component.CPULoadComponent",
            "controller_classpath": "com.stevesoltys.owl.controller.component.CPULoadComponentController"
        }
    ],

    "components": [
        { "identifier": "cpu_load", "update_interval": "1" }
    ],

    "agents": [
        {
            "address": "http://127.0.0.1:8080",
            "username": "username",
            "password": "password",
            "update_interval": "1"
        }
    ],

    "accounts": [
        { "username": "username", "password": "password" }
    ]
}
```

## Development
After checking out the repo, run `gradle build` to install dependencies and build the project. Then, run `gradle test`
to run the tests. You can run `gradle run` to start the application.

## Contributing
Bug reports and pull requests are welcome on GitHub at https://github.com/stevesoltys/owl. This project is intended to
be a safe, welcoming space for collaboration, and contributors are expected to adhere to the
[Contributor Covenant](http://contributor-covenant.org) code of conduct.

## License
This application is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
