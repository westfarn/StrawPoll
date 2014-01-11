## This Code Searches for a word in a directory and replaces that word with a selected word

import os 

top = "C:\\Users\\Jacob\\AndroidStudioProjects\\StrawPoll"
top2 = "C:\Users\Joe\AndroidStudioProjects"
word = "C:\\Users\\westf_000\\AndroidStudioProjects\\StrawPoll" 
change = "C:\\Users\\Jacob\\AndroidStudioProjects\\StrawPoll"

def replace_line(filename) :
	data_write = ""
	fs = open(filename, 'r')
	for line in fs:
		if word in line:
			line = line.replace(word,change)
		data_write += line
	fs.close()
	
	os.remove(filename)
	fs = open(filename, 'w')
	fs.write(data_write)
	fs.flush()
	fs.close()
	
	
	
for root, dirs, files in os.walk(top) :
	for file in files:
		try :
			trigger = False
			filename = os.path.join(root,file)
			fs = open(filename,'r') 		
			for line in fs:
				if word in line:
					trigger = True
					break
			fs.close()	
			if trigger:
				replace_line(filename)
		except Exception, e:
			print("Error"+ str(e))	
	
	

