<template>
<div style="background-color: darkgray">
    <div style="width: 60%;height: 60%;margin-left: 20%" >
        <video-player  class="video-player vjs-custom-skin"
                       :playsinline="true"
                       style="object-fit:fill"
                       :options="playerOptions"
                       :x5-video-player-fullscreen="true"
                       @pause="onPlayerPause($event)"
                       @play="onPlayerPlay($event)"
                       @fullscreenchange="onFullscreenChange($event)"
                       @click="fullScreen"
        ></video-player>
    </div>
</div>
</template>

<script>

    import 'vue-video-player/src/custom-theme.css'
    import "video.js/dist/video-js.css"
    export default {
        created(){

        },
        name: 'BusImg',
        data() {
            return {

                // 视频播放
                playerOptions : {
                    playbackRates: [0.7, 1.0, 1.5, 2.0], //播放速度
                    autoplay: false, //如果true,浏览器准备好时开始回放。
                    muted: false, // 默认情况下将会消除任何音频。
                    loop: false, // 导致视频一结束就重新开始。
                    preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
                    language: 'zh-CN',
                    aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
                    fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
                    sources: [{
                        type: " video/mp4",
                        src: window.sessionStorage.getItem('vvurl')
                        //url地址
                    }],
                    poster: "", //你的封面地址
                    // width: document.documentElement.clientWidth,
                    notSupportedMessage: '此视频暂无法播放，请稍后再试', //允许覆盖Video.js无法播放媒体源时显示的默认信息。
                    controlBar: {
                        timeDivider: true,
                        durationDisplay: true,
                        remainingTimeDisplay: false,
                        fullscreenToggle: true  //全屏按钮
                    }
                }
            }
        },
        computed: {
            player() {
                return this.$refs.videoPlayer.player
            }
        },
        methods:{

            fullScreen() {
                const player = this.$refs.videoPlayer.player;
                player.requestFullscreen();
                player.isFullscreen(true);
                player.play()
            },
            onPlayerPlay(player) {
                player.play()
            },
            onPlayerPause(player) {
                // alert("pause");
            },
        }
    }

</script>

<style>
    .video-js .vjs-big-play-button {
        width: 72px;
        height: 72px;
        border-radius: 100%;
        z-index: 100;
        background-color: #ffffff;
        border: solid 1px #979797;
    }
</style>