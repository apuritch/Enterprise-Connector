namespace CameraWebApp
{
    public interface IImageHttpClient
    {
        Task<HttpResponseMessage> GetLatestImages();
    }

    public class ImageHttpClient : IImageHttpClient
    {
        private HttpClient _client;

        public ImageHttpClient(HttpClient client)
        {
            _client = client;
        }

        public async Task<HttpResponseMessage> GetLatestImages()
        {
            return await _client.GetAsync("");
        }
    }
}
