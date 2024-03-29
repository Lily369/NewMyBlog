'use strict';
/**
 * 获取get请求 指定参数的值
 * @param name 参数名
 * @returns {*}
 * @constructor
 */
function GetQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)');
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return decodeURI(r[2]);
    return null;
}
function randInt(start, end) {
    if (end < start) {
        var mid = end;
        end = start;
        start = mid;
    }
    return Math.floor(Math.random() * (end - start)) + start;
}

/**
 * 获取网易云音乐的歌曲信息
 * @param id 类型可为int，Array
 * @param api 类型为String，是网易云api地址...为可选参数
 * @returns [{},{}..]
 * @constructor
 */
function get163music(id, callback, api) {
    var api = arguments[2] || 'https://api.a632079.me/nm/';
    var t;
    var ids = (Array.isArray(id)) ? id.toString() : id;

    console.log('Song_id:' + ids);
    var xhr = new XMLHttpRequest();
    var music = {};
    //Get Song URL
    console.log('Music id: %s : Loading..', ids);
    t = Date.now();
    xhr.open('get', api + 'summary/' + ids + '?lyric=true', true);
    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4) {
            console.log('Music id: %s Loads Done. Time consumed : %s ms', ids , (Date.now() - t));
            var res = JSON.parse(xhr.responseText);
            if (res && res.ids && res.ids.length > 0) {
                var musicData = []
                for(var Id of res.ids) {
                    var music = {}
                    music.name = res[Id].name;
                    music.artists = '';
                    for (var artistId in res[Id].artists) {
                        if (artistId == (res[Id].artists.length - 1)) {
                            music.artists += res[Id].artists[artistId];
                        } else {
                            music.artists += res[Id].artists[artistId] + ' / ';
                        }
                    }
                    music.url = res[Id].url;
                    music.pic = res[Id].album.picture;
                    music.lyric = (res[Id].lyric.base && res[Id].lyric.base !== '[00:00.00] 纯音乐，敬请聆听。\n') ? res[Id].lyric.base : '';
                    if (music.lyric === '')
                        delete music.lyric;
                    if (res[Id].lyric.translate) {
                        music.tlyric = res[Id].lyric.translate
                    }
                    musicData.push(music);
                }
                callback(musicData);
            } else {
                document.write('id 无效');
            }
        }
    }
    xhr.send()
}
function LoadCplayer(list) {
    list.forEach(function (event) {
        var song_obj = {
            name: event.name,
            artist: event.artists,
            src: event.url,
            poster: event.pic,
            lyric: event.lyric
        };
        if (event.hasOwnProperty('tlyric')) {
            song_obj.sublyric = event.tlyric;
        }
        cplayer_list.push(song_obj);
        if (list[(list.length - 1)] == event) {
            var player = new cplayer({
                element: document.getElementById('app'),
                zoomOutKana: zoomOutKana,
                playmode: playmode,
                volume: volume,
                point: point,
                showPlaylist: showPlaylist,
                autoplay: autoplay,
                width: width,
                size: size,
                style: style,
                playlist: cplayer_list
            });
        }
    });
}

var playlist = '34218357,1359811297,29715402,1351973067';
var zoomOutKana = GetQueryString('zoomOutKana') || false;
if (zoomOutKana == 'false') {
    zoomOutKana = false;
}
var playmode = GetQueryString('playmode') || 'listloop';
var volume = GetQueryString('volume') || 1;
var autoplay = GetQueryString('autoplay') || false;
if (autoplay == 'false') {
    autoplay = false;
}
var showPlaylist = GetQueryString('showPlaylist') || false;
if (showPlaylist == 'false') {
    showPlaylist = false;
}
var width = GetQueryString('width') || '';
var size = GetQueryString('size') || '12px';
var point = GetQueryString('point') || 0;
var style = GetQueryString('style') || '';
var api_url = GetQueryString('api') || 'https://api.a632079.me/nm/';
var cplayer_list = [];
window.onload = function () {
    if (playlist) {
        var ids = [];
        ids = playlist.split(',');
        get163music(ids, LoadCplayer, api_url);
    } else {
        document.write('请填写音乐ID');
    }
}