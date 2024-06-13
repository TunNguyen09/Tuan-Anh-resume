import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore {
	private ArrayList<AudioContent> contents;
	private HashMap<String, Integer> titleMap;
	private HashMap<String, ArrayList<Integer>> artistAndAuthorMap;
	private HashMap<String, ArrayList<Integer>> genreMap;

	public AudioContentStore() {

		try{
			contents = new ArrayList<AudioContent>();
			titleMap = new HashMap<String, Integer>();
			artistAndAuthorMap = new HashMap<String, ArrayList<Integer>>();
			genreMap = new HashMap<String, ArrayList<Integer>>();
			File file = new File("store.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String typeString = scanner.nextLine();
				if (typeString.equals(Song.TYPENAME)) {
				String id = scanner.nextLine();
				String title = scanner.nextLine();
				int year = Integer.parseInt(scanner.nextLine());
				int length = Integer.parseInt(scanner.nextLine());
				String artist = scanner.nextLine();
				String composer = scanner.nextLine();
				String genre = scanner.nextLine();
				int number_of_lines_of_lyrics = Integer.parseInt(scanner.nextLine());
				String lyrics = "";
				for (int i = 0; i < number_of_lines_of_lyrics; i++) {
					lyrics += scanner.nextLine();

				}
				Song song = new Song(title, year, id, typeString, genre, length, artist, composer,
						Song.Genre.valueOf(genre), lyrics);
				contents.add(song);
			}
			if (typeString.equals(AudioBook.TYPENAME)) {
				String id = scanner.nextLine();
				String title = scanner.nextLine();
				int year = Integer.parseInt(scanner.nextLine());
				int length = Integer.parseInt(scanner.nextLine());
				String author = scanner.nextLine();
				String narrator = scanner.nextLine();
				int number_of_chapters_titles = Integer.parseInt(scanner.nextLine());
				ArrayList<String> chapter_Titles = new ArrayList<String>();
				ArrayList<String> chapters = new ArrayList<>();
				for (int i = 0; i < number_of_chapters_titles; i++) {
					chapter_Titles.add(scanner.nextLine());
				}

				int number_of_chapters = Integer.parseInt(scanner.nextLine());
				for (int i = 0; i < number_of_chapters_titles; i++) {
					for (int j = 0; j < number_of_chapters; j++) {
						chapters.add(scanner.nextLine());
					}
				}
				AudioBook audioBook = new AudioBook(title, year, id, typeString, title, length, author, narrator,
						chapter_Titles, chapters);
				contents.add(audioBook);
			}

		}
		//for loop for title
		for (int i = 0; i < contents.size(); i++) {
			AudioContent content = contents.get(i);
			titleMap.put(content.getTitle(), i);

			//for loop for artist and author
			if (content.getType().equals(Song.TYPENAME)) {
				Song song = (Song) contents.get(i);
				if (artistAndAuthorMap.containsKey(song.getArtist())) {
					artistAndAuthorMap.get(song.getArtist()).add(i);
				} else {
					artistAndAuthorMap.put(song.getArtist(), new ArrayList<Integer>());
				}
				if (genreMap.containsKey(song.getGenre().toString())) {
					genreMap.get(song.getGenre().toString()).add(i);
				} else {
					genreMap.put(song.getGenre().toString(), new ArrayList<Integer>());
				}
			}
			if (content.getType().equals(AudioBook.TYPENAME)) {
				AudioBook audioBook = (AudioBook) contents.get(i);
				if (artistAndAuthorMap.containsKey(audioBook.getAuthor())) {
					artistAndAuthorMap.get(audioBook.getAuthor()).add(i);
				}
				else {
					artistAndAuthorMap.put(audioBook.getAuthor(), new ArrayList<Integer>());
				}
			}
		}
		
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		

		// Create some songs audiobooks and podcasts and to store
	// 	String file = "Yesterday, all my troubles";
	// 	contents.add(new Song("Yesterday", 1965, "123", Song.TYPENAME, file, 2, "The Beatles", "Paul McCartney",
	// 			Song.Genre.POP, file));

	// 	file = "I'm sorry if I seem uninterested\n"
	// 			+ "Or I'm not listenin' or I'm indifferent\n"
	// 			+ "Truly, I ain't got no business here\n"
	// 			+ "But since my friends are here, I just came to kick it\n"
	// 			+ "But really I would rather be at home all by myself not in this room\n"
	// 			+ "With people who don't even care about my well being";
	// 	contents.add(new Song("Here", 2015, "391", Song.TYPENAME, file, 3, "Alessia Cara", "Alessia Cara",
	// 			Song.Genre.POP, file));

	// 	file = "Yo, Big Shaq, the one and only\n"
	// 			+ "Man's not hot, never hot\n"
	// 			+ "Skrrat (GottiOnEm), skidi-kat-kat\n"
	// 			+ "Boom\n"
	// 			+ "Two plus two is four\n"
	// 			+ "Minus one that's three, quick maths\n"
	// 			+ "Everyday man's on the block\n"
	// 			+ "Smoke trees (Ah)";
	// 	contents.add(new Song("Man's Not Hot", 2017, "374", Song.TYPENAME, file, 2, "Michael Dapaah", "Michael Dapaah",
	// 			Song.Genre.RAP, file));

	// 	file = "The world was on fire and no one could save me but you\n"
	// 			+ "It's strange what desire will make foolish people do\n"
	// 			+ "I never dreamed that I'd meet somebody like you\n"
	// 			+ "And I never dreamed that I'd lose somebody like you";
	// 	contents.add(new Song("Wicked Game", 1989, "185", Song.TYPENAME, file, 4, "Chris Isaak", "Chris Isaak",
	// 			Song.Genre.ROCK, file));

	// 	file = "The lights go out and I can't be saved\n"
	// 			+ "Tides that I tried to swim against\n"
	// 			+ "Have brought me down upon my knees\n"
	// 			+ "Oh, I beg, I beg and plead\n"
	// 			+ "Singin' come out of things un said";
	// 	contents.add(new Song("Clocks", 2002, "875", Song.TYPENAME, file, 5, "Coldplay", "Guy Berryman, Chris Martin",
	// 			Song.Genre.ROCK, file));

	// 	file = "I'm waking up to ash and dust\n"
	// 			+ "I wipe my brow and I sweat my rust\n"
	// 			+ "I'm breathing in the chemicals";
	// 	contents.add(new Song("Radioactive", 2012, "823", Song.TYPENAME, file, 3, "Imagine Dragons",
	// 			"Josh Mosser, A. Grant, Dan Reynolds, Wayne Sermon, Ben McKee", Song.Genre.ROCK, file));

	// 	file = "Birds flying high\n"
	// 			+ "You know how I feel\n"
	// 			+ "Sun in the sky\n"
	// 			+ "You know how I feel\n"
	// 			+ "Breeze driftin' on by\n"
	// 			+ "You know how I feel\n"
	// 			+ "It's a new dawn\n"
	// 			+ "It's a new day\n"
	// 			+ "It's a new life\n"
	// 			+ "For me";
	// 	contents.add(new Song("Feelin' Good", 1965, "875", Song.TYPENAME, file, 3, "Nina Simone",
	// 			"Anthony Newley, Leslie Bricusse", Song.Genre.JAZZ, file));

	// 	file = "Find table spaces, say your social graces\n"
	// 			+ "Bow your head, they're pious here\n"
	// 			+ "But you and I, we're pioneers, we make our own rules\n"
	// 			+ "Our own room, no bias here";
	// 	contents.add(new Song("Wild Things", 2015, "443", Song.TYPENAME, file, 4, "Alessia Cara", "Alessia Cara",
	// 			Song.Genre.POP, file));

	// 	AudioBook book = new AudioBook("Harry Potter and the Goblet of Fire", 2015, "894", AudioBook.TYPENAME, "", 1236,
	// 			"J.K. Rowling", "Jim Dale", makeHPChapterTitles(), makeHPChapters());
	// 	contents.add(book);

	// 	book = new AudioBook("Moby Dick", 2018, "376", AudioBook.TYPENAME, "", 1422,
	// 			"Herman Melville", "Pete Cross", makeMDChapterTitles(), makeMDChapters());
	// 	contents.add(book);

	// 	book = new AudioBook("Shogun", 2018, "284", AudioBook.TYPENAME, "", 3213,
	// 			"James Clavel", "Ralph Lister", makeSHChapterTitles(), makeSHChapters());
	// 	contents.add(book);

	// 	Podcast podcast = new Podcast("The Secret Life of Canada", 2021, "865", Podcast.TYPENAME,
	// 			"Leah-Simone Bowen, Falen Johnson", makeSeasons());
	// 	contents.add(podcast);
	}

	public ArrayList<AudioContent> getContent(int index1, int index2) throws IndexOutOfBoundsException{
		// if (index1 < 0 || index2 > contents.size() || index1 > index2) {
		// 	return null;
		// }
		ArrayList<AudioContent> downloadedContents = new ArrayList<AudioContent>();
		// return contents.get(index - 1);
		for (int i = index1 - 1; i < index2; i++) {
			downloadedContents.add(contents.get(i));
		}
		return downloadedContents;
	}

	public void listAll() {
		for (int i = 0; i < contents.size(); i++) {
			int index = i + 1;
			System.out.print(index + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
	}

	public ArrayList<AudioContent> download_Artist(String artistName) {
		ArrayList<Integer> index = artistAndAuthorMap.get(artistName);
		ArrayList<AudioContent> artistAndAuthor = new ArrayList<AudioContent>();
		for (int i : index) {
			artistAndAuthor.add(contents.get(i));
		}
		return artistAndAuthor;
	}

	public ArrayList<AudioContent> download_Genre(String genre) {
		ArrayList<Integer> index = genreMap.get(genre);
		ArrayList<AudioContent> genreList = new ArrayList<AudioContent>();
		for (int i : index) {
			genreList.add(contents.get(i));
		}
		return genreList;
	}

	public void search(String title) {
		int index = titleMap.get(title);
		System.out.println(index + 1 + ". ");
		contents.get(index).printInfo();
		System.out.println();
	}

	public ArrayList<AudioContent> searchArist(String artist) {
		ArrayList<AudioContent> artists = new ArrayList<AudioContent>();
		ArrayList<Integer> index = artistAndAuthorMap.get(artist);
		for (int i : index) {
			artists.add(contents.get(i));
			System.out.print(i + 1 + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
		return artists;
	}

	public ArrayList<AudioContent> searchAuthor(String author) {
		ArrayList<AudioContent> authors = new ArrayList<AudioContent>();
		ArrayList<Integer> index = artistAndAuthorMap.get(author);
		for (int i : index) {
			authors.add(contents.get(i));
			System.out.print(i + 1 + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
		return authors;
	}

	public ArrayList<AudioContent> searchGenre(String genre) {
		ArrayList<AudioContent> genrContents = new ArrayList<AudioContent>();
		ArrayList<Integer> index = genreMap.get(genre);
		for (int i : index) {
			genrContents.add(contents.get(i));
			System.out.print(i + 1 + ". ");
			contents.get(i).printInfo();
			System.out.println();
		}
		return genrContents;
	}
	

	// private ArrayList<String> makeHPChapterTitles() {
	// 	ArrayList<String> titles = new ArrayList<String>();
	// 	titles.add("The Riddle House");
	// 	titles.add("The Scar");
	// 	titles.add("The Invitation");
	// 	titles.add("Back to The Burrow");
	// 	return titles;
	// }

	// private ArrayList<String> makeHPChapters() {
	// 	ArrayList<String> chapters = new ArrayList<String>();
	// 	chapters.add("In which we learn of the mysterious murders\n"
	// 			+ "in the Riddle House fifty years ago, \n"
	// 			+ "how Frank Bryce was accused but released for lack of evidence, \n"
	// 			+ "and how the Riddle House fell into disrepair. ");
	// 	chapters.add("In which Harry awakens from a bad dream, \n"
	// 			+ "his scar burning, we recap Harry�s previous adventures, \n"
	// 			+ "and he writes a letter to his godfather.");
	// 	chapters.add("In which Dudley and the rest of the Dursleys are on a diet,\n"
	// 			+ "and the Dursleys get letter from Mrs. Weasley inviting Harry to stay\n"
	// 			+ "with her family and attend the World Quidditch Cup finals.");
	// 	chapters.add("In which Harry awaits the arrival of the Weasleys, \n"
	// 			+ "who come by Floo Powder and get trapped in the blocked-off fireplace,\n"
	// 			+ "blast it open, send Fred and George after Harry�s trunk,\n"
	// 			+ "then Floo back to the Burrow. Just as Harry is about to leave, \n"
	// 			+ "Dudley eats a magical toffee dropped by Fred and grows a huge purple tongue. ");
	// 	return chapters;
	// }

	// private ArrayList<String> makeMDChapterTitles() {
	// 	ArrayList<String> titles = new ArrayList<String>();
	// 	titles.add("Loomings.");
	// 	titles.add("The Carpet-Bag.");
	// 	titles.add("The Spouter-Inn.");
	// 	return titles;
	// }

	// private ArrayList<String> makeMDChapters() {
	// 	ArrayList<String> chapters = new ArrayList<String>();
	// 	chapters.add("Call me Ishmael. Some years ago�never mind how long precisely�having little\n"
	// 			+ "or no money in my purse, and nothing particular to interest me on shore,\n"
	// 			+ "I thought I would sail about a little and see the watery part of the world.");
	// 	chapters.add("stuffed a shirt or two into my old carpet-bag, tucked it under my arm, \n"
	// 			+ "and started for Cape Horn and the Pacific. Quitting the good city of old Manhatto, \n"
	// 			+ "I duly arrived in New Bedford. It was a Saturday night in December.");
	// 	chapters.add("Entering that gable-ended Spouter-Inn, you found yourself in a wide, \n"
	// 			+ "low, straggling entry with old-fashioned wainscots, \n"
	// 			+ "reminding one of the bulwarks of some condemned old craft.");
	// 	return chapters;
	// }

	// private ArrayList<String> makeSHChapterTitles() {
	// 	ArrayList<String> titles = new ArrayList<String>();
	// 	titles.add("");
	// 	titles.add("");
	// 	titles.add("");
	// 	titles.add("");
	// 	return titles;
	// }

	// private ArrayList<String> makeSHChapters() {
	// 	ArrayList<String> chapters = new ArrayList<String>();
	// 	chapters.add("The gale tore at him and he felt its bite deep within\n"
	// 			+ "and he knew that if they did not make landfall in three days they would all be dead");
	// 	chapters.add("Blackthorne was suddenly awake. For a moment he thought he was dreaming\n"
	// 			+ "because he was ashore and the room unbelieveable");
	// 	chapters.add("The daimyo, Kasigi Yabu, Lord of Izu, wants to know who you are,\n"
	// 			+ "where you come from, how ou got here, and what acts of piracy you have committed.");
	// 	chapters.add("Yabu lay in the hot bath, more content, more confident than he had ever been in his life.");
	// 	return chapters;
	// }

	// // Podcast Seasons
	// private ArrayList<Season> makeSeasons() {
	// 	ArrayList<Season> seasons = new ArrayList<Season>();
	// 	Season s1 = new Season();
	// 	s1.episodeTitles.add("Bay Blanket");
	// 	s1.episodeTitles.add("You Don't Want to Sleep Here");
	// 	s1.episodeTitles.add("The Gold Rush");
	// 	s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \n"
	// 			+ "lip-syncing, but some people believe they were used to spread\n"
	// 			+ "smallpox and decimate entire Indigenous communities. \n"
	// 			+ "We dive into the history of The Hudson's Bay Company and unpack the\n"
	// 			+ "very complicated story of the iconic striped blanket.");
	// 	s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \n"
	// 			+ "But what did the mining industry cost the original people of the territory? \n"
	// 			+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
	// 	s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \n"
	// 			+ "But what did the mining industry cost the original people of the territory? \n"
	// 			+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
	// 	s1.episodeLengths.add(31);
	// 	s1.episodeLengths.add(32);
	// 	s1.episodeLengths.add(45);
	// 	seasons.add(s1);
	// 	Season s2 = new Season();
	// 	s2.episodeTitles.add("Toronto vs Everyone");
	// 	s2.episodeTitles.add("Water");
	// 	s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \n"
	// 			+ "But what did the mining industry cost the original people of the territory? \n"
	// 			+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
	// 	s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\n"
	// 			+ "In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\n"
	// 			+ "and vampires, and discuss some big concerns currently facing Canada's water.");
	// 	s2.episodeLengths.add(45);
	// 	s2.episodeLengths.add(50);

	// 	seasons.add(s2);
	// 	return seasons;
	// }

	
	//create a map for title
	// private HashMap<String, Integer> createTitleMap()
	// {
	// 	HashMap<String, Integer> titleMap = new HashMap<>();
	// 	for (int i = 0; i < contents.size(); i++) {
	// 		AudioContent content = contents.get(i);
	// 		if (content instanceof Song) {
	// 			Song song = (Song) content;
	// 			String titleSong = song.getTitle();
	// 			titleMap.put(titleSong, i);
	// 		}
	// 	}
	// 	return titleMap;
	// }

	//create a map for title

}
