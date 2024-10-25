import os
# opening the path
path = r"C:\Users\Samah Diab\OneDrive\Desktop\Python_test" \
      

# loop over a path using the os.walk to get
# the dir_path, dir_names, and filenames to be used in the loop
for directory_path, directory_names, names in os.walk(path):
    # assigning the new directory in the loop
    new_directory = directory_path.replace(path, "")
    # using the str.count and the sep here is the / or \ in the the path
    new_directory = new_directory.count(os.sep)
    # assigning the indentation we wanna use I choose a TAP
    indent = "\t"
    # the next line is not my own idea but here I couldn't do it on my own
    # to format the results after trail and error
    # but now I totally understand it I hope for next times
    print(f"{indent*new_directory}{os.path.basename(directory_path)}")
    for f in names:
        print(f"{indent*(new_directory + 1)} {f}")
print()  
