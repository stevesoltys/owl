module OwlComponentControllerModule

  java_import 'com.stevesoltys.owl.controller.OwlComponentController'

  class RubyComponentController < OwlComponentController

    def initialize(&block)
      super()
      instance_eval(&block)
    end

    def on(event, &block)

      case event
        when :initialize
          @initialize_block = block

        when :update
          @update_block = block
      end

    end

    def init(component)
      @initialize_block.call(component) unless @initialize_block.nil?
    end

    def update(component)
      @update_block.call(component) unless @update_block.nil?
      component.last_update = Time.now
    end
  end

end

def create_component_controller(&block)
  return OwlComponentControllerModule::RubyComponentController.new(&block)
end