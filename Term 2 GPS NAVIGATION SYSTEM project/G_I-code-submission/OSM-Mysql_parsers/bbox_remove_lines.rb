# @author Kirill Blazhko
# This script extracts small areas from large .osm files


def remove_empty_lines(from,to)
# Define the file to write to:

	output = File.new(to, 'w')

#Set counters:
	total_counter = 0
	skipped_counter = 0
	closers = 0

#Bounding box coordinates:
	left_lon=11.572   
	right_lon=12.886  
	top_lat=57.941  
	bottom_lat=57.377  


#These regexps find the lines with nodes which have coordinates:
	lon_regex = /lon="\d*.\d*/
	lat_regex = /lat="\d*.\d*/
	waiting_for_node_to_close = false

	IO.foreach(from) do |line|
	if line =~ /<node/   
	puts "Found a line with a node"
	puts line
	unless line.scan(lon_regex).to_s.scan(/\d+.\d+/).to_s.to_f < left_lon or  line.scan(lon_regex).to_s.scan(/\d+.\d+/).to_s.to_f > right_lon or line.scan(lat_regex).to_s.scan(/\d+.\d+/).to_s.to_f > top_lat or line.scan(lat_regex).to_s.scan(/\d+.\d+/).to_s.to_f < bottom_lat 
	puts "Found a node in the bounding box"
	output.puts line
	puts "Added the line with this node to the output file"
	else 
	if line =~ /\/>/
	puts "Found a node out of bounds, skipping it in place (it closes on the same line)"
	skipped_counter += 1
	else
	puts "Found a node out of bounds, skipping it until it is </node>"
	waiting_for_node_to_close = true
	skipped_counter += 1
	if waiting_for_node_to_close
	puts "Waiting for skipped node to close"
	end
	end
	end

	else
	puts "Found a non-node line: " + line
	puts "Waiting for closing tag? " + waiting_for_node_to_close.to_s

#This part deals with multi-line node elements:

	if !waiting_for_node_to_close
	puts "Writing line from file to file: " + line
	puts total_counter.to_s
	output.puts line
	else
	if line =~ /<\/node>/
	puts "Found closing tag of the node that is out of the bounds: " + line
	waiting_for_node_to_close = false
	else
	puts "Skipping embedded elements of skipped node"
	skipped_counter += 1
	closers += 1
	end
	end

	end

	puts "*"*32
	puts "Skipped " + skipped_counter.to_s + " of total " + total_counter.to_s
	puts "Skipped " + skipped_counter.to_s + " of total " + total_counter.to_s
	puts "Skipped " + skipped_counter.to_s + " of total " + total_counter.to_s
	puts "Skipped " + skipped_counter.to_s + " of total " + total_counter.to_s
	puts "*"*32
	total_counter += 1
	puts "Found closing tags of skipped nodes: " + closers.to_s

	end

	output.close

	end

#Input and output files can be changed here:

	remove_empty_lines("sweden.osm","extracted_map.osm")
	puts "Done"

