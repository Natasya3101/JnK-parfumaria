
function VideoSlider  ()  {


  

  return (
    <div className="w-full p-5" style={{ height: 'calc(100vh - 110px)' }}>
      <iframe
        className="w-full h-full object-cover"
        src={'https://www.youtube.com/embed/zpDza9rd9_4'}
        title="Video Slider"
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
        allowFullScreen
      ></iframe>
    </div>
  );
};

export default VideoSlider;
