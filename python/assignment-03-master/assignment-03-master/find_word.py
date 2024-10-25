def read_file(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        res = f.readlines()
    return res


def contains_digit(word):
    for m in word:
        if m.isdigit():
            return True
    return False



def get_words(row: str):
    words = row.lower().split(" ")
    symbol = [",", ".","!","?",":",";"]
    new_words = []
    word_ = ""
    condition = (len(word_) == 1 and word_[0] == "a" or len(word_) == 1 and word_[0] == "i")
    for word in words:
        word_ = ""
        for char in word:
            if char.isalpha() and char.isascii():
                word_ += char
            elif char == "-" or char == "'":
                word_ += char
        
        if condition:
            new_words.append(word_)
            continue

        if word_ != "" and word_[0].isalpha() and len(word_) > 1:
            new_words.append(word_)
        
    return new_words




def save_words(file_path, words: list):
    with open(file_path, "w",encoding='utf-8') as file:
        for word in words:
            file.write(word + "\n")




    # Read text files
    holy_grail_path = "holy_grail.txt"
    holz_grail_copy = "holy_grail_copy.txt"
    eng_sentences_path = "eng_news_100k-sentences.txt"
    eng_sentences_copy = "eng_news_100k-sentences_copy.txt"
    rows = read_file(holy_grail_path, eng_sentences_path)
    print(f"\nRead {len(rows)} lines from file {holy_grail_path}")

    # Collect words
    words = []
    for row in rows:
        w = get_words(row)  # Returns a list of words
        words += w

    # Save words in file
    outpath = holz_grail_copy
    save_words(outpath,words)
    print(f"Saved {len(words)} words in file {outpath}") # About 1.8 million words