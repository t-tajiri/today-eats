Feature: 設定画面_ご飯の設定
  ユーザーは自分で好きなご飯を設定したい

  Background:
    Given 設定画面を表示する

  Scenario: 好きなご飯を登録する
    When ご飯を入力する
    When カテゴリを選択する
    When ご飯を登録するボタンを押す
    Then 設定が保存される

  Scenario: 好きなご飯の登録内容を変更する
    When ご飯の登録内容を変更する
    When 変更ボタンを押す
    Then 設定が保存される

  Scenario: 好きなご飯の登録を解除する
    When 削除したいご飯の削除ボタンを押す
    Then 設定が保存される
    Then 削除したいご飯が一覧から消えている
