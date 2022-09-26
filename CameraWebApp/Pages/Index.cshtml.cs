using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace CameraWebApp.Pages
{
    public class IndexModel : PageModel
    {
        private readonly ILogger<IndexModel> _logger;
        private readonly IWebHostEnvironment _env;

        public string UpdatedTime { get; set; }

        public IndexModel(ILogger<IndexModel> logger, IWebHostEnvironment env)
        {
            _logger = logger;
            _env = env;
            UpdatedTime = "";
        }

        public void OnGet()
        {
            var filePath = $"{_env.WebRootPath}/images/images.zip";
            var fileInfo = new FileInfo(filePath);
            UpdatedTime = fileInfo.LastWriteTimeUtc.ToString("yyyy-MM-dd HH:mm:ss");
        }
    }
}