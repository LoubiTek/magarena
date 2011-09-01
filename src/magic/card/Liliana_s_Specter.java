package magic.card;

import magic.model.MagicGame;
import magic.model.MagicPermanent;
import magic.model.MagicPlayer;
import magic.model.event.MagicDiscardEvent;
import magic.model.event.MagicEvent;
import magic.model.trigger.MagicTrigger;
import magic.model.trigger.MagicTriggerType;

public class Liliana_s_Specter {
    public static final MagicTrigger T = new MagicTrigger(MagicTriggerType.WhenComesIntoPlay) {
        @Override
		public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent) {
            final MagicPlayer player = permanent.getController();
            final MagicPlayer opponent = game.getOpponent(player);
			return new MagicEvent(
                    permanent,
                    player,
                    new Object[]{permanent,opponent},
                    this,
                    opponent + " opponent discards a card.");
		}
		@Override
		public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object data[],
                final Object[] choiceResults) {
			final MagicPermanent permanent=(MagicPermanent)data[0];
			final MagicPlayer player=(MagicPlayer)data[1];
			game.addEvent(new MagicDiscardEvent(permanent,player,1,false));
		}		
    };
}
