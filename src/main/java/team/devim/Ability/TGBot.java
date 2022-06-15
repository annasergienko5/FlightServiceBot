package team.devim.Ability;

import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.telegram.abilitybots.api.objects.Flag.DOCUMENT;
import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class TGBot extends AbilityBot {

    public Ability replyToDocumentGet() {
        return Ability.builder()
                .name(DEFAULT)
                .flag(DOCUMENT)
                .privacy(PUBLIC)
                .locality(ALL)
                .input(0)
                .action(ctx -> {
                    silent.send("Секундочку", ctx.chatId());
                    Document document = ctx.update().getMessage().getDocument();
                    Service service = new Service();
                    SendDocument sendDocument;
                    if (Constants.PROCESS_GET) {
                        sendDocument = service.processGET(document, ctx.chatId().toString());
                    } else {
                        sendDocument = service.processPOST(document, ctx.chatId().toString());
                    }
                    try {
                        execute(sendDocument);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                })
                .build();
    }

    @Override
    public long creatorId() {
        return Integer.parseInt(Constants.CREATOR_ID);
    }

    public TGBot() {
        super(Constants.BOT_TOKEN, Constants.BOT_USERNAME);
    }
}