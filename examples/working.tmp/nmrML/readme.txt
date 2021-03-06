What was done to generate the instance1modifiedToBiosample-concentrations.xml file under nmrML\examples\nmrML  ?

We analysed, if our schema accompensated for all data required by the Cruz example. The original J Cruz nmrML XML example at http://www.metabolomicscentre.ca/nmrML/biosample-concentrations.xml
was transliterated into an instance xml generated via Oxygen as described at http://www.oxygenxml.com/doc/ug-editor/topics/xml-schema-instance-generator.html. The xsd instance was generated under the following settings: All optional elements and attributes were created. Max 2 repetitions. Incremental attribute/element names were used for the default examples. Type alternative strategy: First. Numbers for count attributes are set to 50 but need not necessarily be instantiated all. Optional elements below nesting level 6 were discarded.
Schema URL: file:/C:/nmrML/xml-schemata/nmrML.xsd
Namespace: http://nmrml.org/schema
Root Element: nmrML

Where the correct entity usage for some values were doubtful, value entries were marked with the String "???". Not used elements and attributes (containing the mere default autogenerated values were deleted in the final version.
Where the correct values were not known from the Cruz example, we either put "n/a" or left that value the default autocreated example value, e.g. see solventConcentration for such an example.

Keep in mind that the xerces xml parser still puts out many error messages, i.e. for datatype violations and dangling ID/IDREF bindings (also where for mockup CV terms no ontology reference is given in cvList).

In general the Cruz example is concerned with the data on an experiment in its later (post-processed) stage. As quantifications are to be stored in an own xsd there is currently no mapping for the Cruz <metaboliteConcentrationList

There is no homolog in our xsd for the Cruz xml example at  
<sampleList count="1">
    <sample id="sample1" name="1-methylhistidine">

This would be captured in the sample metadata, i.e. ISA Tab ???
 Or to be put in fileContent UserParam ?
    
The following questions arose from this instantiation effort:
What exactly do we have to capture in referenceableParamGroupList ? Examples?

ID vs name in elements, e.g. software ID is name ? // unique numeric ID

What was the acquisition nucleus in the Cruz experiment ? what fieldFrequencyLockName ?

What has to be put in the value field for chemical shift standard ? isnt the CV term enough? // be more flexible here

What does the order in the processingMethod element do ?
    
The following element needs to be altered in order to be able to specify the actual unit of Measurement, here "Temperature", so better use CVParamWithUnitType instead of ValueWithUnitType:
<sampleAcquisitionTemperature value="25" unitAccession="UO:0000027" unitName="degree celsius" unitCvRef="UO"/>

Why do we need an own Type for Temperature Unit ? It is not referenced anywhere in the current xsd anyway, so delete ?