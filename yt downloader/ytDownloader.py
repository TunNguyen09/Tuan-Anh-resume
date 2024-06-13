import yt_dlp
import sys
import os

def download_video(link, download_folder, username, password):
    if not os.path.exists(download_folder):
        os.makedirs(download_folder)

    ydl_opts = {
        'outtmpl': os.path.join(download_folder, '%(title)s.%(ext)s'),  # Output filename template
        'username': username,
        'password': password,
    }

    with yt_dlp.YoutubeDL(ydl_opts) as ydl:
        try:
            ydl.download([link])
            print(f"Download completed! Video saved to: {download_folder}")
        except Exception as e:
            print(f"An error occurred: {e}")

if __name__ == "__main__":
    if len(sys.argv) > 4:
        link = sys.argv[1]
        download_folder = sys.argv[2]
        username = sys.argv[3]
        password = sys.argv[4]
        download_video(link, download_folder, username, password)
    else:
        print("Error: Missing arguments. Usage: python ytDownloader.py <video_link> <download_folder> <username> <password>")
