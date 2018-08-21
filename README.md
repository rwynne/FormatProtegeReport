RWW: This file was last updated on 8/21/18

FormatProtegeReport v1.1
========================
This program will take a Protege 5 report and format it to your specifications
(declared in a configuration file).  The input is the raw report from Protege
and the outputs are a text .txt and Excel .xls files.

Requirements:
Java 1.8 or higher

Instruction of use
------------------
1. Export a report from Protege 5 based on any number of columns, specified
by a slot and optional qualifiers.  The option for column headers must be selected.
Columns must be delimited by a tab, and values must be delimited by the vertical bar.  
Save this file anywhere on your file system with a .txt extension. It is suggested 
to save this <yourfilename>.txt file to this folder for ease of command-line entry.

2. Create or modify a config file to your desired output specifications.  The default config 
file (which will work with the example input TestReport1) is in the config folder.  The program 
will default to using this config file if none is specified from the command-lin.

Each column of the desired output is configurable.  The parameters expected by each column include
the following, followed by an equals sign, and then the desired value.  All entries for a column 
should begin with ColumnNumber.

ColumnNumber=<integer>
Property=<the property code in the raw file>
*QualifierName=<a qualifier name of a property value in the raw file>
*QualifierValue<the qualifier value in the raw file>

* there may be zero or more Qualifiers specified in the configuration.  If none is
specified, all properties will be ouput (without JSON markup if qualifiers exist).

Any configuration file with errors in it will be reported by the program.  For example, if
there is a typo on QualifierName (e.g. QualferNAme) the program will abort letting you know
of the erroneous line.

3. Once you have your raw input file from Protege and configuration file set, the program is 
ready to be run.

4. Open the command-line on Windows, or a terminal on Mac/Linux and change directory to this folder (a shortcut
   to the cmd prompt is included for convenience if using Windows that will open in this directory).

   - If on Windows, start by typing FormatProtegeReport.bat.
   - If on Mac/Linux, start by typing FormatProtegeReport.sh

   Follow this script by typing the optional and required parameters as such:

   - Windows:
   C:\FormatProtegeReport>FormatProtegeReport [OPTIONS] [RAW REPORT INPUT FILE] [EXCEL OUTPUT FILE]

   - Mac:
   $./FormatProtegeReport.sh [OPTIONS] [RAW REPORT INPUT FILE] [EXCEL OUTPUT FILE]

   Example from the included test input file:

   C:\>FormatProtegeReport CTRP_Full.txt CTRP_Pretty.xls

   If you would like to create another config file and use it, the -C or --config option may be used with
   the location of the config file specified.

   C:\>FormatProtegeReport -C ".\config\configExample2.txt" CTRP_Full.txt CTRP_Pretty.xls
   $./FormatProtegeReport.sh -C "./config/configExample2.txt" CTRP_Full.txt CTRP_Pretty.xls


Known issues
------------
The ColumnNumber is currently meaningless.  The ordering of columns will be top to bottom
of the config file.

Bug reports and feature requests may be submitted to Rob for future releases.

Change log
==========

Version 1.0 -> 1.1
------------------
* Protege report exports are now exported with doubly double quotes.  The parser
  for these values has been updated to remove them.

*  UTF-8 special characters (e.g. prime symbols) are now imported into the .xls 
   to show as intended.

*  A .txt version of the report is generated.
