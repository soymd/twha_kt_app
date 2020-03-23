'use strict';

function ZoomBar() {
  const zoom_bar = document.getElementById('scale-zoom');
  const cursor = document.getElementById('scale-zoom-cursor');
  let on_changed_handler = null;
  let zoom_move = 0;
  let new_zoom = 0;

  // オリジナルtwhaでは116になっていたが、ずれることがあったため修正
  const ZOOM_BAR_POSITION_ADJUSTMENT = 126;

  // 「つまみ」を動かす
  function update_cursor() {
    cursor.style.left = (101 - data.zoom * 16) + 'px';
  }
  function zoom_limit() {
    if (data.zoom < 0) {
      data.zoom = 0;
    } else if (data.zoom > 4) {
      data.zoom = 4;
    }
  }

  function setZoomMove() {
    if (new_zoom < 0) {
      zoom_move--;
    } else if (new_zoom > 4) {
      zoom_move++;
    }
  }

  this.update = update_cursor;

  this.onchanged = function (f) {
    on_changed_handler = f;
  };

  zoom_bar.addEventListener('mousedown', function (e) {
    // マウス座標からつまみ位置を求める
    new_zoom = Math.floor((ZOOM_BAR_POSITION_ADJUSTMENT - e.clientX) / 16);
    console.log(e.clientX);

    // 座標が0以上4以下だった場合
    if (new_zoom >= 0 && new_zoom <= 4) {
      // そのままズーム倍率を設定
      data.zoom = new_zoom;
      zoom_limit();
      update_cursor();
      if (on_changed_handler) {
        on_changed_handler();
      }
      // 座標が0より大きい or 4より大きかった場合
    } else {
      // つまみを動かす方向を求める
      setZoomMove();
      data.zoom += zoom_move;
      zoom_limit();
      update_cursor();
      if (on_changed_handler) {
        on_changed_handler();
      }
      // つまみを動かす方向を0にリセット
      zoom_move = 0;
    }
  });

  update_cursor();
}

/*
ピンチイン・ピンチアウト処理未実装

  let touchstart_bar = 0;
  let touchmove_bar = 0;

  infoLayer.addEventListener('touchstart', function (e) {
    touchstart_bar = 0;
    touchmove_bar = 0;
    if (e.touches.length > 1) {
      //絶対値を取得
      w_abs_start = Math.abs(e.touchs[1].pageX - e.touchs[0].pageX);
      h_abs_start = Math.abs(e.touchs[1].pageY - e.touchs[0].pageY);
      //はじめに2本指タッチした時の面積
      touchstart_bar = w_abs_start * h_abs_start;
    }
  }, false);

  infoLayer.addEventListener('touchmove', function (e) {
    if (e.touches.length > 1) {
      //絶対値を取得
      w_abs_move = Math.abs(e.touchs[1].pageX - e.touchs[0].pageX);
      h_abs_move = Math.abs(e.touchs[1].pageY - e.touchs[0].pageY);
      //ムーブした時の面積
      touchmove_bar = w_abs_move * h_abs_move;
      //はじめに2タッチ面積からムーブした時の面積を引く
      area_bar = touchstart_bar - touchmove_bar;
      if (area_bar < 0) {//拡大する
        data.zoom++;
        zoom_limit();
        update_cursor();
        if (on_changed_handler) {
          on_changed_handler();
        }
      }
      else if (area_bar > 0) {//縮小する
        data.zoom--;
        zoom_limit();
        update_cursor();
        if (on_changed_handler) {
          on_changed_handler();
        }
      }
    }

  });
*/
