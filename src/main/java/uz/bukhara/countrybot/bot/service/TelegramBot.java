package uz.bukhara.countrybot.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.net.URISyntaxException;

public interface TelegramBot {
    SendMessage mainMenuSend(Update update);

    EditMessageText mainMenuEdit(Update update);

    EditMessageText menuInfo(Update update);

    EditMessageText searchCountryEdit(Update update);

    SendMessage searchCountrySend(Update update);

    SendMessage searchWithLocation(Update update);

    EditMessageText menuSearch(Update update);

    EditMessageText searchByWritingAnything(Update update);

    SendMessage searchFromInputSend(Update update);

    EditMessageText searchFromInputEdit(Update update);

    EditMessageText setDataOfCountry(Update update);

    EditMessageText setCurrencyOfCountry(Update update);

    EditMessageText setLanguageOfCountry(Update update);

    EditMessageText setBordersOfCountry(Update update);

    EditMessageText setTimeZonesOfCountry(Update update);

    SendDocument setFlagOfCountry(Update update) throws IOException, URISyntaxException;

    SendMessage removeKeyboardMarkup(Update update);

    DeleteMessage deleteTwoPrevMessage(Update update);

    DeleteMessage deletePrevMessage(Update update);
}
