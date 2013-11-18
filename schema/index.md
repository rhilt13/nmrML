---
layout: main
title: nmrML
nav:
  docs: active
---

You can view the documentation and download current and past releases here:

<table class="table">
<thead>
<tr><th>nmrML Version</th><th>Documenation</th><th>Download</th></tr>
</thead>
<tbody>
<tr><td>v1.0.rc1</td><td><a href="/schema/v1.0.rc1/doc" >View Documentation</a><td><td><a href="/schema/v1.0.rc1/nmrML.xsd">nmrML Schema</a></td></tr>
</tbody>
</table>

### nmrML Overview

We had several overarching goals that guided our decision making process. The data format should:

* Allow 1D and 2D NMR spectra and raw data to be easily shared in a vendor agnostic manner
* Record enough information about an NMR spectrum acquisition to allow for further processing of the raw spectrum without referring to the original vendor files.
* The data format should reference the original files for the sake of posterity and in the case where original vendor specific information is required.
* The data format should be flexible and allow for multiple use cases of NMR experiments.
* The data format should be easy for developers to understand and integrate into software.

As in our PSI role model, we agreed on implementing a combined standard using XML and accompanying CV terms (see ![nmrML structure](/images/nmrML_Structure.gif)), as this allows multiple validation levels to be established: XML syntax and structural validity of XML instances (xml element and attribute positions, order and cardinality) are validated by the XML parser against the XML Schema.

The mapping files enforce semantic validity  by specifying which CV terms are allowed in an element as well as the order and cardinality those terms. A proprietary validator tool, to be developed for the next deliverable) checks that the criteria outlined by the mapping file are being met in a given XML instance. The mapping file combined with the CV can also be used when creating an interface that records NMR experiment information for example to populate a drop down menu or an autocomplete box.
