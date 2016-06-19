
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