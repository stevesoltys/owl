##
# The native module. Encapsulates native library function calls for use within plugins.
module Native

  include_package 'com.stevesoltys.owl.plugin.jna.linux'

  java_import 'com.sun.jna.NativeLibrary'
  java_import 'com.sun.jna.Platform'

  ##
  # A list of C functions which return pointers.
  POINTER_FUNCTIONS = %w(malloc calloc fopen)

  ##
  # The standard libraary for this platform.
  STANDARD_LIBRARY = NativeLibrary::get_instance('c')

  def self.method_missing(name, *args)
    name = name.to_s

    if POINTER_FUNCTIONS.include?(name)
      STANDARD_LIBRARY.get_function(name).invoke_pointer(args.to_java)
    else
      STANDARD_LIBRARY.get_function(name).invoke_int(args.to_java)
    end

  end

end