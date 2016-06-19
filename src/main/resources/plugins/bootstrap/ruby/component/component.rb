module OwlComponentModule

  java_import 'com.stevesoltys.owl.model.OwlComponent'

  class OwlComponent

    def method_missing(symbol, *args)
      key = symbol.to_s.strip

      if key[-1] == '='
        raise "Expected argument count of 1, received #{args.length}" unless args.length == 1

        key = key[0...-1].strip # Drop the equals and trim whitespace.
        value = args[0]
        attributes.put(key, value)

      elsif attributes.contains_key(key)
        return attributes.get(key)

      else
        super(symbol, *args)
      end

    end
  end

  ##
  # Deep converts all keys in the given hash into strings.
  #
  # @param [Hash] hash The hash.
  def self.stringify(hash)
    return hash.inject({}) { |memo, (k, v)| memo[k.to_s] = stringify(v); memo } if hash.is_a? Hash
    return hash
  end

end

##
# Registers a component type, given the identifier, controller, and attributes.
#
# @param [String] identifier The component's identifier.
# @param [OwlComponentController] controller The component's controller.
# @param [Hash] attributes The component's attribute hash.
def register_component_type(identifier, controller, attributes={})
  $application.register_component_type(identifier, controller, OwlComponentModule::stringify(attributes))
end