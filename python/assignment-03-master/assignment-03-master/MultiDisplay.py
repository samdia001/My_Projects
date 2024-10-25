class MultiDisplay:

    def __init__(self):
        self.message = ""
        self.count = 0

    def set_message(self,string):
        self.message = string

    def set_count(self,cnt):
        self.count = cnt

    def display(self):
        for _ in range(self.count):
            print(self.message)

    def set_display(self,msg , cnt):
        self.message = msg
        self.count = cnt
        for _ in range(self.count):
            print(self.message)

    def to_string(self) :
        return "Message: " + self.message + ", " + "Count: " + str(self.count)

    def get_message(self) :
        return self.message