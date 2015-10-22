# DMD_Java4Life
Autumn semester project for the DMD course by the team named "Java4Life": Bulat Mukhutdinov, Nikolay Yushkevich, Timur Shakirov.

Features:
* Realtime StAX parser of DBLP database
* Both HTML and console loggers
* GZ decompressor
* Creation of DB schema, according to DTD structure
* Maven

Run the application with following JVM properties: -Xmx1G -DentityExpansionLimit=25000000

0. article – An article from a journal or magazine.
0. inproceedings – A paper in a conference or workshop proceedings.
0. proceedings – The proceedings volume of a conference or workshop.
0. book – An authored monograph or an edited collection of articles.
0. incollection – A part or chapter in a monograph.
0. phdthesis – A PhD thesis.
0. mastersthesis – A Master's thesis. There are only very few Master's theses in dblp.
0. www – A web page. There are only very few web pages in dblp. See also the notes on person records.
