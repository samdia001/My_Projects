import os 
import math 
sqrt = math.sqrt

#File A
file_path = r"C:\Users\Samah Diab\OneDrive\Desktop\assigment003\g\file_10000integers_A.txt"
with open(file_path, "r") as file_A:
    values = file_A.readlines() 
    values_a = [c.strip('\n').split(',') for c in values] 

converted_a = [[int(numbers) for numbers in a] for a in values_a]
file_A.close()

a_final_values = []
for s in converted_a:
    for v in s:
        a_final_values.append(v)
        

file_b_path  = r"C:\Users\Samah Diab\OneDrive\Desktop\assigment003\g\file_10000integers_B.txt"
with open(file_b_path, "r") as file_B:
    values = file_B.readlines() 
    values_b = [c.strip('\n').split(':') for c in values] 
converted_b = [[int(numbers) for numbers in b] for b in values_b]
file_B.close()

b_final_values = []
for s in converted_b:
    for v in s:
        b_final_values.append(v)

lst = a_final_values
lst2 = b_final_values

def mean(lst):
    m = sum(lst)/len(lst)
    return m 

def std(lst):
    length = len(lst)
    m = mean(lst)
    total_sum = 0
    for i in range(length): 
        total_sum += (lst[i]-m)**2
    return sqrt(total_sum*1.0/length)
print(f'File.A\nMean: {round(mean(lst))}\nStdv: {round(std(lst))}') 
print() 
print(f'File.B\nMean: {round(mean(lst2))}\nStdv: {round(std(lst2))}')  
