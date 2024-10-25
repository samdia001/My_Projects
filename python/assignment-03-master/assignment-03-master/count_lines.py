import os
dir_path = r"C:\Users\Samah Diab\OneDrive\Desktop\assigment003"


def count_lines(path):
    # counts the number of code lines in a file
    lines = 0
    with open(path, 'r', encoding='utf8') as python_file:
        for line in python_file:
            if line.strip():
                lines += 1
    return lines


def count_py_lines(path):
    # counting python lines in a directory/sub-directory
    directory_content = os.scandir(path)
    total_lines = 0
    for content in directory_content:
        if content.name.startswith('.'):
            continue
        elif content.is_dir():
            # recursion which had to get most of it from the internet as using a function in itself!
            # sorry but to be honest didn't get much from the lectures regarding that!
            total_lines += count_py_lines(content)
        elif content.name.endswith('.py'):
            total_lines += count_lines(content)
    return total_lines


print(f'Total number of python lines is: {count_py_lines(dir_path)}')
