using Hangfire;
using System.IO.Compression;

namespace CameraWebApp
{
    public interface IImageService
    {
        Task GetImages();
    }
    public class ImageService : IImageService
    {
        private IImageHttpClient _client;
        private IWebHostEnvironment _env;

        public ImageService(IImageHttpClient client, IWebHostEnvironment env)
        {
            _client = client;
            _env = env;
        }

        [AutomaticRetry(Attempts = 0)]
        public async Task GetImages()
        {
            var response = await _client.GetLatestImages();
            
            using var stream = await response.Content.ReadAsStreamAsync();
            using var mStream = new MemoryStream();

            await stream.CopyToAsync(mStream);

            var directory = Path.Combine(_env.WebRootPath, "images");
            var filePath = Path.Combine(directory, "images.zip");

            File.WriteAllBytes(filePath, mStream.ToArray());

            ZipFile.ExtractToDirectory(filePath, directory, true);
        }
    }
}
