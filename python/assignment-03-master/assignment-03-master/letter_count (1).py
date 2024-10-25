import os

# allowed letters
letters = "abcdefghijklmnopqrstuvwxyz"

frequency = {}

# Iterate through each path
# explained in another file but self explanatory


path = r'C:\Users\leo\OneDrive' \
           r'\Desktop\Synced Studies' \
           r'\Introduction to programming - 21HT-1DV5011DT901' \
           r'\python_courses\1DT901\ln223dq_assign3' \
           r'\large_texts.txt'

for files in os.listdir(path):
    new_path = f"{path}/{files}"

    file = open(os.path.join(new_path), encoding='utf-8')
    print()
    print("#" * 150)

    # loop through each line of the opened file
    for line in file:
        # lowercase
        text = line.strip().lower()
        # loop through letters
        for ch in text:
            # If exists in letters
            if ch in letters:
                # if exists in the frequency dct
                if ch in frequency.keys():
                    # increase frequency 1
                    frequency[ch] += 1
                else:
                    # else create a key !
                    frequency[ch] = 1

    # Print out the filename just read
    print(f"File read : {files}")
    # Print out total number of letters
    print(f"Number of letters : {sum(frequency.values())}")

    # calculating min
    XXX = min(frequency.values())

    # Printing the histogram
    print(f"Histogram where each star represents {XXX} occurrences of the given letters")

    # loop in letters and frequency dictionary
    for letter in sorted(frequency.keys()):
        # Print out the letter and stars
        print("{} | {}".format(letter, "*" * int(frequency[letter] / XXX)))

    print(("#" * 150) + "\n")
