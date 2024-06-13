import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
//import java.lang.Exception;
import java.lang.IndexOutOfBoundsException;
// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args)
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your library
		AudioContentStore store = new AudioContentStore();
		
		// Create my music library
		Library library = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				library.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				library.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				library.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				library.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				library.listAllPlaylists(); 
			}
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				int index1 = 0;
				int index2 = 0;

				System.out.print("From Store Content #: ");
				if (scanner.hasNextInt()) {
					index1 = scanner.nextInt();
					//scanner.nextLine(); // consume nl
				}
				System.out.print("To Store Content #: ");
				if (scanner.hasNextInt()) {
					index2 = scanner.nextInt();
					scanner.nextLine();
				}
				try {
					ArrayList<AudioContent> content = store.getContent(index1, index2);
					library.download(content);
				} catch (IndexOutOfBoundsException e) {
					System.out.println(e.getMessage());
				}
				// if (content == null)
				// 	//System.out.println("Content Not Found in Store");
				// 	throw new AudioContentNotFoundException("Content Not Found in Store");

				// else if (!library.download(content))
				// 		//System.out.println(library.getErrorMessage());
				// 		throw new AudioContentIsAlreadyDownloaded(
				// 				content.getType() + content + "already downloaded");
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (AudioContentIsAlreadyDownloaded e) {
					System.out.println(e.getMessage());
				}

			}
			else if (action.equalsIgnoreCase("DOWNLOADA")) {
				String artistName = "";
				System.out.print("Artist Name: ");
				if (scanner.hasNextLine()) {
					artistName = scanner.nextLine();
				}
				try {
					// ArrayList<Integer> artistName2 = store.download_Artist(artistName);
					// library.downloadA(artistName2);;
					ArrayList<AudioContent> artistName2 = store.download_Artist(artistName);
					library.downloadA(artistName2);

				} catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("DOWNLOADG")) {
				String genreName = "";
				System.out.print("Genre Name: ");
				if (scanner.hasNextLine()) {
					genreName = scanner.nextLine();
				}
				try{
					ArrayList<AudioContent> genreName2 = store.download_Genre(genreName);
					library.donwloadG(genreName2);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			

			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				int index = 0;

				System.out.print("Song Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				// consume the nl character since nextInt() does not
					scanner.nextLine(); 
				}
				// if (!library.playSong(index))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.playSong(index);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
					
			}
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt()) {
					index = scanner.nextInt();
					scanner.nextLine();
				}
				// if (!library.printAudioBookTOC(index))
				// 	System.out.println(library.getErrorMessage());	
				try{
					library.printAudioBookTOC(index);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
			}
			
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				int index = 0;

				System.out.print("Audio Book Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				int chapter = 0;
				System.out.print("Chapter: ");
				if (scanner.hasNextInt())
				{
					chapter = scanner.nextInt();
					scanner.nextLine();
				}
				// if (!library.playAudioBook(index, chapter))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.playAudioBook(index, chapter);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				int index = 0;
				int season = 0;
				
				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
				}
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				// if (!library.printPodcastEpisodes(index, season))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.printPodcastEpisodes(index, season);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
			}
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				int index = 0;

				System.out.print("Podcast Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				int season = 0;
				System.out.print("Season: ");
				if (scanner.hasNextInt())
				{
					season = scanner.nextInt();
					scanner.nextLine();
				}
				int episode = 0;
				System.out.print("Episode: ");
				if (scanner.hasNextInt())
				{
					episode = scanner.nextInt();
					scanner.nextLine();
				}
				// if (!library.playPodcast(index, season, episode))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.playPodcast(index, season, episode);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
					
			}
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				// if (!library.playPlaylist(title))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.playPlaylist(title);
				}	
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
			}
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				String title = "";
        int index = 0;
        
				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				System.out.print("Content Number: ");
				if (scanner.hasNextInt())
				{
					index = scanner.nextInt();
					scanner.nextLine();
				}
				// if (!library.playPlaylist(title, index))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.playPlaylist(title, index);	
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch (InvalidInput e) {
					System.out.println(e.getMessage());
				}
				
			}
			// Delete a song from the library and any play lists it belongs to
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				int songNum = 0;

				System.out.print("Library Song #: ");
				if (scanner.hasNextInt())
				{
					songNum = scanner.nextInt();
					scanner.nextLine();
				}
				
				// if (!library.deleteSong(songNum))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.deleteSong(songNum);
				}	
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
				{
					title = scanner.nextLine();
				}
				// if (!library.makePlaylist(title))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.makePlaylist(title);
				}
				catch (PlayListAlreadyExisted e) {
					System.out.println(e.getMessage());
				}
					
			}
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				String title = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					title = scanner.nextLine();

				// if (!library.printPlaylist(title))
				// 	System.out.println(library.getErrorMessage());
				try{
					library.printPlaylist(title);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
			}
			// Add content from library (via index) to a playlist
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				int contentIndex = 0;
				String contentType = "";
        String playlist = "";
        
        System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();
        
				System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
				if (scanner.hasNextLine())
					contentType = scanner.nextLine();
				
				System.out.print("Library Content #: ");
				if (scanner.hasNextInt())
				{
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				
				// if (!library.addContentToPlaylist(contentType, contentIndex, playlist))
				// 	System.out.println(library.getErrorMessage());	
				try{
					library.addContentToPlaylist(contentType, contentIndex, playlist);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
				
			}
			// Delete content from play list
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				int contentIndex = 0;
				String playlist = "";

				System.out.print("Playlist Title: ");
				if (scanner.hasNextLine())
					playlist = scanner.nextLine();

				System.out.print("Playlist Content #: ");
				if (scanner.hasNextInt()) {
					contentIndex = scanner.nextInt();
					scanner.nextLine(); // consume nl
				}
				// if (!library.delContentFromPlaylist(contentIndex, playlist))
				// 	System.out.println(library.getErrorMessage());
				try {
					library.delContentFromPlaylist(contentIndex, playlist);
				} catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}

			}
			else if (action.equalsIgnoreCase("SEARCH")) {
				String title = "";
				System.out.print("Title: ");
				if (scanner.hasNextLine()) {
					title = scanner.nextLine();
				}
				try {
					store.search(title);
				} catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHA")) {
				String artist = "";
				System.out.print("Artist: ");
				if (scanner.hasNextLine()) {
					artist = scanner.nextLine();
				}
				try{
					store.searchArist(artist);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SEARCHG")) {
				String genre = "";
				System.out.print("Genre: ");
				if (scanner.hasNextLine()) {
					genre = scanner.nextLine();
				}
				try{
					store.searchGenre(genre);
				}
				catch (AudioContentNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				library.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				library.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				library.sortSongsByLength();
			}

			System.out.print("\n>");
		}
	}
}
