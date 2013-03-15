package org.spek.console.cmd

import org.spek.impl.events.*
import org.spek.console.reflect.*
import org.spek.console.listeners.*

fun main(args: Array<String>) {
    if (args.size < 2) {
        printUsage()
    } else {
        val options = getOptions(args)
        val specRunner = setupRunner(options)
        specRunner.runSpecs(options.path, options.packageName)
    }
}

fun getOptions(args: Array<String>): Options {
    var path = ""
    var textPresent = false
    var htmlPresent = false
    var filename = ""
    var cssFile = ""
    var packageName = ""
    if (args.size >= 2) {
        path = args[0]
        packageName = args[1]
        textPresent = args.find { it.contains("-text")} != null
        htmlPresent = args.find { it.contains("-html")} != null
        val filePos = args.toList().indexOf("-file")
        if (filePos > 0) {
            filename = args[filePos+1]
        }
        val cssPos = args.toList().indexOf("-css")
        if (cssPos > 0) {
            cssFile = args[cssPos+1]
        }
    }
    return Options(path, packageName, textPresent, htmlPresent, filename, cssFile)
}

fun setupRunner(options: Options): SpecificationRunner {

    val listeners = arrayListOf<Listener>()
    val multipleNotifiers = MultipleListenerNotifier(listeners)

    var device: OutputDevice
    if (options.filename != "") {
        device = FileDevice(options.filename)
    } else {
        device = ConsoleDevice()
    }
    if (options.toText) {
        listeners.add(PlainTextListener(device))
    }
    if (options.toHtml) {
        listeners.add(HTMLListener(device, options.cssFile))
    }
    return SpecificationRunner(multipleNotifiers)
}
