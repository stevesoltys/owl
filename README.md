# Owl
[![Build Status](https://travis-ci.org/stevesoltys/owl.svg?branch=master)](https://travis-ci.org/stevesoltys/owl)  

Owl is a systems and network monitoring application written in Java using Spring.

## Configuration
By default, Owl will look for a JSON configuration file in your home directory: ```$HOME/.config/owl/config.json```.

An example configuration file can be seen below:

```json
{
    "components": [
        { "identifier": "cpu_load", "update_interval": 1, "warning_threshold": 4.0, "critical_threshold": 5.0 }
    ],

    "agents": [
        { "address": "http://127.0.0.1:8080", "update_interval": 1, "username": "username", "password": "password" }
    ],

    "accounts": [
        { "username": "username", "password": "password" }
    ]
}

```

## Plugins
Owl relies upon plugins for monitoring components. Generally, component plugins are written using the provided domain
specific language.

Here's an example using JRuby, the CPU load monitoring plugin:

```ruby
# A module containing the CPU load monitoring plugin.
module CPULoadModule

  java_import 'java.lang.management.ManagementFactory'

  # The identifier for this plugin.
  IDENTIFIER = 'cpu_load'

  # A hash containing the attributes for this plugin.
  ATTRIBUTES = {
      :load => 0.0
  }.freeze

  # The component controller for this plugin.
  CONTROLLER = create_component_controller do

    # The critical threshold configuration key.
    CRITICAL_THRESHOLD = 'critical_threshold'

    # The warning threshold configuration key.
    WARNING_THRESHOLD = 'warning_threshold'

    # The operating system bean instance.
    OPERATING_SYSTEM = ManagementFactory::get_operating_system_mx_bean

    on :update do |cpu|
      cpu.load = OPERATING_SYSTEM.system_load_average
      cpu.state = cpu_state(cpu)
    end

    # Updates the current state of the given CPU load component.
    def cpu_state(cpu)
      cpu.state = ComponentState::OK

      cpu.state = ComponentState::WARNING if cpu.load >= cpu.configuration[WARNING_THRESHOLD]
      cpu.state = ComponentState::CRITICAL if cpu.load >= cpu.configuration[CRITICAL_THRESHOLD]
    end
  end

  register_component_type(IDENTIFIER, CONTROLLER, ATTRIBUTES)
end
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
