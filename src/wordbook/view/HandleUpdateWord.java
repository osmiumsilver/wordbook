package wordbook.view;

import wordbook.data.QueryOneWord;
import wordbook.data.UpdateWord;
import wordbook.data.Word;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HandleUpdateWord implements ActionListener {
    UpdateWordView view;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.lookWord) {
            lookWord();
        } else if (e.getSource() == view.submit) {
            updateWord();
        }
    }

    private void updateWord() {
        String englishWord = view.inputWord.getText();
        String meaning = view.inputNewMeaning.getText();
        if (englishWord.length() == 0 || meaning.length() == 0)
            return;
        Word word = new Word();
        UpdateWord update = new UpdateWord();
        word.setEnglishWord(englishWord);
        word.setMeaning(meaning);
        int isOK = update.updateWord(word);
        if (isOK != 0)
            view.hintMess.setText("更新单词成功");
        else
            view.hintMess.setText("更新失败，单词不在表里");
    }

    private void lookWord() {
        String englishWord = view.inputWord.getText();
        if (englishWord.length() == 0)
            return;
        Word word = new Word();
        word.setEnglishWord(englishWord);
        QueryOneWord query = new QueryOneWord();
        Word result = query.queryOneWord(word);
        if (result != null) {
            view.inputNewMeaning.setText(result.getMeaning());
        } else
            view.hintMess.setText("单词不在表里");
    }

    public void setView(UpdateWordView view) {
        this.view = view;
    }
}