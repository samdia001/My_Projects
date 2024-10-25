import os

def List(path):
    lst = []
    with open(path, "r") as file:
        str = file.read()

        if ", " in str:
            str = str.replace("\n", ", ")

            string_lst = str.split(", ")

            for s in string_lst:
                if s != "":
                    lst.append(int(s))

        if ":" in str:
            string_lst = str.split(":")

            for s in string_lst:
                lst.append(int(s))

        return lst

def count_different(lst):
   return len(set(lst))

def count_occurences(lst):
    d = {i: lst.count(i) for i in lst}

    sorted_lst = sorted(d.items(), key=lambda item: item[1], reverse=1)

    return sorted_lst[0:5]

file_path : str = "file_10000integers_A.txt"

lst = List(file_path)
print('file A diff:', count_different(lst))
print('file A diff:', count_occurences(lst))

file_path_b : str = "file_10000integers_B.txt"

lst = List(file_path_b)
print('file B diff:', count_different(lst))
print('file B diff:', count_occurences(lst))
