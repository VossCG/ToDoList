[33mcommit f4a5fcc1380d98856926cd10ede489700605b459[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m)[m
Author: Voss <yot4106@gmail.com>
Date:   Wed Apr 13 15:04:12 2022 +0800

    SearchFragment 相關更動
    
    1.editText關鍵字搜尋功能完成
    2.監聽keyboard 的actionDone 去執行搜尋

[33mcommit 008b45051370c023458eabb6ba564e1150e18011[m
Author: Voss <yot4106@gmail.com>
Date:   Tue Apr 12 17:54:22 2022 +0800

    SearchFragment 相關更動
    
    1.新稱搜尋按鈕
    2.editText 外觀優化
    3.rowItem 布局優化
    
    待處理
    搜尋功能需改為關鍵字title搜尋

[33mcommit 1641a7159ccf428fbb5ccab3ee1b2998c440a461[m
Author: Voss <yot4106@gmail.com>
Date:   Tue Apr 12 16:40:30 2022 +0800

    SearchFragment 相關更動
    1.新增recyclerview
    2.新增對應adapter
    3.新增recycler item 點擊擴展layout
    
    待處理
    1.搜尋EditText資料拿取
    2.資料過濾後導入Adapter

[33mcommit 610f29aad1039095129a1279fc9673796cfd4aec[m
Author: Voss <yot4106@gmail.com>
Date:   Mon Apr 11 16:54:10 2022 +0800

    1.在contentItem 新增刪除功能
    2.在ViewHolder 當中去綁定刪除功能，若是在OnBindViewHolder 去實作 position會出現異常，因為刪除後會再重新呼叫，跑出outindexpointer exception

[33mcommit 13d92bd1a7765d44ecd37cd0a842744d99d2d937[m
Author: Voss <yot4106@gmail.com>
Date:   Mon Apr 11 15:47:27 2022 +0800

    1.新增content row中的 點擊icon編輯功能
    2.在跳轉到MonthFragment 讓navbottom消失

[33mcommit 832630d4effe9a30100d67606c409f2109a0de22[m
Author: Voss <yot4106@gmail.com>
Date:   Sat Apr 9 15:06:28 2022 +0800

    second commit
    
    新增content item 點擊跳轉updateFragment

[33mcommit bc7b27955163cbc404303ff84395a90d4880dc06[m
Author: Voss <yot4106@gmail.com>
Date:   Sat Apr 9 14:26:34 2022 +0800

    first commit
