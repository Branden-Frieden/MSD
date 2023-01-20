import os

# find file in parent directory and open
parentDirectory = os.path.dirname(os.path.abspath(__file__))
input_file = open(os.path.join(parentDirectory, "traced_route"))

# read the file lines
lines = input_file.readlines()

# create output file for exporting data
output_file = open(os.path.join(parentDirectory, "output2"), "w+")

# go through each line of the document
for line in lines:
    address = ""
    times = []
    startParenthases = False
    endParenthases = False
    time = ""
    for index in range(len(line)):
        char = line[index]

        # find the start parenthases and store characters in a string until the end parenthases is hit
        if(startParenthases):
            if(char == ')'):
                startParenthases = False
                endParenthases = True
                continue
            else:
                address += char

        if(char == '('):
            startParenthases = True
        
        # After the end parenthases, look for numbers or '.', this is the time, go until the m in ms
        if(endParenthases):
            if(char.isnumeric() or char == '.'):
                time += char
            if(char == 'm'):
                times.append(float(time))
                time = ""
        
    # if the address is long enough to be a real address, write to output file
    if(len(address) > 8):
        output_file.write("\n")
        output_file.write(address)
        averageDelay = str(sum(times) / len(times))
        output_file.write(" ")
        output_file.write(f'{averageDelay:.4} ms')
