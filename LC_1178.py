class Solution:
    # [1]
	# Generates a bit mask given a word, where the i-th bit being 1 mean
	# the word contains the i-th letter of the alphabet.
	# For example the word 'acf' -> 100101 because a, c, f are the 1st, 3rd,
	# and 6th letters of the alphabet, so those corresponding bits are 1.
    def getBitMask(self, word: str) -> int:
        mask = 0
        for c in word:
		    # Maps 'a' -> 0, 'b' -> 1, 'c' -> 2, ...
            i = ord(c) - ord('a')
			# Sets the i-th bit to 1.
            mask |= 1 << i
        return mask

    def findNumOfValidWords(self, words: List[str], puzzles: List[str]) -> List[int]:
	    # [2]
		# Maps the bit mask for every word to the count of words with that same bit mask.
		# For example 'abd' and 'baddd' would have the same mask because they are composed
		# of the same set of characters.
        letterFrequencies = {}
        for word in words:
            mask = self.getBitMask(word)
            letterFrequencies[mask] = letterFrequencies.get(mask, 0) + 1
        
        solution = [0] * len(puzzles)
        
        for i in range(len(puzzles)):
            puzzle = puzzles[i]
            mask = self.getBitMask(puzzle)
            subMask = mask
            total = 0
			
			# The index of the first bit in the puzzle. We need this to check if the
			# submasks we generate are of valid words.
            firstBitIndex = ord(puzzle[0]) - ord('a')

			# [3]
            # In this while loop we want to go through all possible "submasks" of the bit
			# mask for the current puzzle. If our puzzle bit mask is 1011, for example, we
			# would generate 1011, 1010, 1001, 1000, 0011, 0010, 0001, 0000
            while True:
				# [4]
			    # If this submask contains the first letter of the puzzle, it's a valid word. Here
				# we add to the number of words we've seen with this mask to our total.
                if subMask >> firstBitIndex & 1:
                    total += letterFrequencies.get(subMask, 0)
				# We've exhausted all possible submasks.
                if subMask == 0:
                    break
				# Get rid of the right-most bit, and restore any bits to the right of it that were
				# originally in the mask. If the original mask was '01011' current submask is '01000',
				# then submask - 1 = '00111' and (submask - 1) & mask = '00011'.
                subMask = (subMask - 1) & mask
            solution[i] = total
        
        return solution
