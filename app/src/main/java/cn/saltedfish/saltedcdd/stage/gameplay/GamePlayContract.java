package cn.saltedfish.saltedcdd.stage.gameplay;

import java.util.List;

import cn.saltedfish.saltedcdd.game.card.Card;
import cn.saltedfish.saltedcdd.stage.BasePresenter;
import cn.saltedfish.saltedcdd.stage.BaseView;

public interface GamePlayContract {
    interface View extends BaseView<Presenter> {
        void setPauseMenuVisibility(boolean pVisible);

        void setPauseButtonVisibility(boolean pVisible);

        void setPlayerInfo(int index, String pNickname, PlayerInfoGameView.AvatarType pAvatarType);

        void playDealCardsAnimation();

        void setPlayerCards(int index, List<Card> pCards);

        void showPlayerPass(int index);

        void showPlayerShowCard(int index, List<Card> pCards);

        void showTurnToMyself(boolean pShowCard, boolean pPass);

        void showTurnToOthers(int index);

        List<Card> getCardSelection();

        void setCardSelection(List<Card> pCards);

        void clearCardSelection();

        void setPlayerAvatar(int index, PlayerInfoGameView.AvatarType pAvatarType);

        void showGameResult();

        void setResultMyselfRank(int pRank);

        void setResultPlayerRank(int index, String pNickname, int pScore);

        void runOnUiThread(Runnable pRunnable);

        void showToast(String pToast);

        void repaint();
    }

    interface Presenter extends BasePresenter {
        void onRestartGameClicked();

        void onResumeGameClicked();

        void onPauseGameClicked();

        void onQuitGameClicked();

        void onShowCardClicked();

        void onPassClicked();

        void onSwitchAutoplay();

        void onHintClicked();
    }
}
