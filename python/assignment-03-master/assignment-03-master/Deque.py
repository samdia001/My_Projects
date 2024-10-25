from dataclasses import dataclass
from typing import Any

# A head-and-tail implementation of a deque using data classes


# Each node is an instance of class Node
@dataclass
class Node:
    value: int = None
    nxt: Any = None  # Any since Node not properly defined at this point


@dataclass
class Deque:
    head: Node = None   # First node in queue
    tail: Node = None      # Last node in queue
    size: int = 0

    # Add element n as first entry in deque
    def add_first(self, n) -> None:
        node  = Node(n, self.head)
        if self.head is None:
            self.head = node
            self.tail = node
        else:
            node.nxt = self.head
            self.head = node
        self.size += 1
           
            


    # Returns a string representation of the current deque content
    def to_string(self):
        if self.size == 0:
            return "{ }"

        output = "{"
        itr = self.head
        while itr:
            output += f" {itr.value}"
            if itr.nxt is None:
                output += "}" 
                return output
            itr = itr.nxt


    # Add element n as last entry in deque
    def add_last(self, n):
        node = Node(n, None)
        if self.head is None:
            self.head = node
            self.tail = node
        else:
            self.tail.nxt = node
            self.tail = node
        self.size += 1    

    # Returns (without removing) the last entry in the deque.
    # Gives error message and returns None when accessing empty deque.
    def get_last(self):
        if not self.head:
            print("You can't access an empty queue")
            return None
        return self.tail.value

    # Returns (without removing) the first entry in the deque
    # Gives error message and returns None when accessing empty deque.
    def get_first(self):
        if not self.head :
            print("Get can't be applied on an empty list")
            return None
        return self.head.value    

    # Removes and returns the first entry in the deque.
    # Gives error message and returns None when accessing empty deque.
    # The case size = 1 requires speciall handling
    def remove_first(self):
        if self.head is None:
            print("Remove can't be applied on an empty list")
            return None

        itr = self.head
        p = 0
        tail : Node = None

        while itr:
            if itr.nxt is None:
                p = itr
                itr = None
                self.tail = tail
                if self.size == 1:
                    self.head = None
                if self.tail:
                    self.tail.nxt = None
            else:
                tail = itr
                itr = itr.nxt
        self.size -= 1

        return p.value           

    # Removes and returns the last entry in the deque.
    # Gives error message and returns None when accessing empty deque.
    # The case size = 1 requires speciall handling
    def remove_last(self):
        if not self.head:
            print("Remove can't be applied on an empty list")
            return None

        p = self.head.value
        self.head = self.head.nxt
        self.size -= 1

        return p
