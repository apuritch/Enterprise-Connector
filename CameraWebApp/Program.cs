using CameraWebApp;
using Hangfire;
using Hangfire.MemoryStorage;
using Microsoft.Extensions.Configuration;
using System.Net.Http.Headers;

var builder = WebApplication.CreateBuilder(args);

//Hangfire
builder.Services.AddHangfire(x => x.UseMemoryStorage());
builder.Services.AddHangfireServer();

// Add services to the container.
builder.Services.AddRazorPages();

builder.Services.AddHttpClient<IImageHttpClient, ImageHttpClient>(client =>
{
    var url = builder.Configuration.GetValue<string>("ImageApi:Url");
    client.BaseAddress = new Uri(url);
    var ssa = builder.Configuration.GetValue<string>("ImageApi:Ssa");
    client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("SharedAccessSignature", ssa);
});

builder.Services.AddScoped<IImageService, ImageService>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapRazorPages();

app.UseHangfireDashboard();

var interval = builder.Configuration.GetValue<string>("Hangfire:Interval");
RecurringJob.AddOrUpdate<ImageService>("GetImages", x => x.GetImages(), $"*/{interval} * * * *");
Console.WriteLine($"Recurring Job Registered with the interval {interval} minutes");

app.Run();
