# SymmetricEncryptionBenchmarking
System requirements:
- 500MB of storage space
- min 6GB of RAM
- Windows OS
- Java JDK 1.8

How to use:
- extract the "Executables" archive, in a dedicated folder, anywhere on disk
- run the "RunTheJar.cmd" file (it will run the benchamrk .jar file, with extended java heap memory allowence)
- follow on screen instructions (recommended to press 0)
After the last algorithm has succesfully finished (Twofish) (message: "press any button to close window" will appear)
- run "Extractor.cmd" file (it will run the ConvertResultsToCSV.jar file)
- the CSV file will be located in the generated IPC/Results folder

Estimated time of run: 5 hours (depending on hardware configuration).

This repository consists of two projects: IPC, Extractor.
IPC is the main benchmark driver, it will generate all necessary resources (the files that are to be encrypted and decrypted)
(will use ~500MB of memory), run all 8 algorithms (DES, AES, 3DES, Blowfish, Twofish, Serpent, RC5, and RC6), both in ecryption
and decryption mode, 100 times for every mode, for every file; then create a "Results.txt" file where all of the results will be saved.

Extractor project is meant to convert the initial Results.txt file into a much easier to handle .csv file
