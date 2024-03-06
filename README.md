# file-folder-utility
Program to assist in making folders and organizing files.
Made using NetBeans.

## Create tab:

Drop one or more files into the top left window, and the program will read the filename(s).

The assumed format for filenames is "[prefix] #####[-#####] (company)", e.g. "PO 12345 SUPPLIER NAME" or "SO 54320-54321 CUSTOMER NAME".

The program accounts for some variations in number ranges, e.g. "SO 54320 - 54321 CUSTOMER NAME" or "SO 54320-1 CUSTOMER NAME".

The proposed folder name(s) will appear in the right window.

The lower left window can be used to drop in any supplemental files you want transferred into the folders as well.

There is a default safeguard against number ranges greater than 100, which can be disabled using the checkbox.

Clicking the Clear button will remove everything and start from an empty slate.

Clicking the Create button will create the folders listed in the right window, copy/move* the files listed in the top left window into their respective** folders, and copy/move* the files listed in the bottom left into all the folders.

The folders are created within the same directory their respective** files existed in.

_*The files are copied into folders until the program reaches the last folder, then the files are moved into the last folder._

_**If there are multiple files in the top left window, each one will be handled separately, i.e. one or more folders are created for the first file, then that file is copy/moved into its folders, then the cycle begins again on the second file, etc._

After clicking the Create button, and after the program runs, the windows and internal variables will be cleared out.

Some errors are accounted for, and if triggered a message in red will appear above the Create button.

## Copy tab:

Drop one or more files into the top window.

Drop one or more folders into the bottom window.

The first checkbox can be used to prevent the file(s) from being moved into the last folder*. If checked, the file(s) will only be copied.

By default, files will not overwrite existing files. The second checkbox can be used to disable this restriction.

Clicking the Clear button will remove everything and start from an empty slate.

Clicking the Copy button will copy/move* all the files in the the top window into all the folders in the bottom window.

After the program runs, the windows and internal variables will be cleared out.

Some errors are accounted for, and if triggered a message in red will appear above the Copy button.
