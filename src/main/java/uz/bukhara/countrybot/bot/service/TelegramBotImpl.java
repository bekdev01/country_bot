package uz.bukhara.countrybot.bot.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.bukhara.countrybot.bot.CountryBot;
import uz.bukhara.countrybot.bot.model.countryModel.CurrenciesItem;
import uz.bukhara.countrybot.bot.model.countryModel.LanguagesItem;
import uz.bukhara.countrybot.bot.model.countryModel.Response;
import uz.bukhara.countrybot.bot.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramBotImpl implements TelegramBot {


    @Override
    public SendMessage mainMenuSend(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.MENU_TEXT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }

        row.add(new InlineKeyboardButton(Constant.SEARCH_COUNTRY).setCallbackData(Constant.SEARCH_COUNTRY));
        row.add(new InlineKeyboardButton(Constant.INFO).setCallbackData(Constant.INFO));
        return sendMessage;
    }

    @Override
    public EditMessageText mainMenuEdit(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.MENU_TEXT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        row.add(new InlineKeyboardButton(Constant.SEARCH_COUNTRY).setCallbackData(Constant.SEARCH_COUNTRY));
        row.add(new InlineKeyboardButton(Constant.INFO).setCallbackData(Constant.INFO));
        return sendMessage;
    }

    @Override
    public EditMessageText menuInfo(Update update) {
        Message message = update.getCallbackQuery().getMessage();
        List<InlineKeyboardButton> row=new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_MENU));
        List<List<InlineKeyboardButton>> rowList=new ArrayList<>();
        rowList.add(row);
        return new EditMessageText()
                .setMessageId(message.getMessageId())
                .setChatId(message.getChatId())
                .setText(Constant.INFO_TEXT)
                .setReplyMarkup(new InlineKeyboardMarkup().setKeyboard(rowList));
    }

    @Override
    public EditMessageText searchCountryEdit(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        rowList.add(row1);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.SEARCH_COUNTRY_TEXT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        row.add(new InlineKeyboardButton(Constant.SET_TEXT).setCallbackData(Constant.SET_TEXT));
        row.add(new InlineKeyboardButton(Constant.SEARCH_WITH_LOCATION).setCallbackData(Constant.SEARCH_WITH_LOCATION));
        row1.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_MENU));

        return sendMessage;
    }

    @Override
    public SendMessage searchCountrySend(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        rowList.add(row1);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.SEARCH_COUNTRY_TEXT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }

        row.add(new InlineKeyboardButton(Constant.SET_TEXT).setCallbackData(Constant.SET_TEXT));
        row.add(new InlineKeyboardButton(Constant.SEARCH_WITH_LOCATION).setCallbackData(Constant.SEARCH_WITH_LOCATION));
        row1.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_MENU));

        return sendMessage;
    }

    @Override
    public SendMessage searchWithLocation(Update update) {

        KeyboardRow row1=new KeyboardRow();
        KeyboardRow row2=new KeyboardRow();
        row1.add(new KeyboardButton(Constant.SET_LOCATION).setRequestLocation(true));
        row2.add(Constant.BACK);
        List<KeyboardRow> rowList=new ArrayList<>();
        rowList.add(row1);
        rowList.add(row2);
        ReplyKeyboardMarkup replyKeyboardMarkup=new ReplyKeyboardMarkup()
                .setKeyboard(rowList)
                .setOneTimeKeyboard(false)
                .setResizeKeyboard(true)
                .setSelective(true);

        return new SendMessage()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setReplyMarkup(replyKeyboardMarkup)
                .setText(Constant.SET_LOCATION_TEXT);
    }

    @Override
    public EditMessageText menuSearch(Update update) {
        CountryBot.searchBy = null;
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        rowList.add(row1);
        rowList.add(row2);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.SEARCH_TEXT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        row.add(new InlineKeyboardButton(Constant.SEARCH_BY_NAME).setCallbackData(Constant.SEARCH_BY_NAME));
        row.add(new InlineKeyboardButton(Constant.SEARCH_BY_CAPITAL).setCallbackData(Constant.SEARCH_BY_CAPITAL));
        row.add(new InlineKeyboardButton(Constant.SEARCH_BY_REGION).setCallbackData(Constant.SEARCH_BY_REGION));
        row1.add(new InlineKeyboardButton(Constant.SEARCH_BY_LANGUAGE).setCallbackData(Constant.SEARCH_BY_LANGUAGE));
        row1.add(new InlineKeyboardButton(Constant.SEARCH_BY_CURRENCY).setCallbackData(Constant.SEARCH_BY_CURRENCY));
        row1.add(new InlineKeyboardButton(Constant.SEARCH_BY_CALLING_CODE).setCallbackData(Constant.SEARCH_BY_CALLING_CODE));
        row2.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_SEARCH_COUNTRY));

        return sendMessage;
    }

    @Override
    public EditMessageText searchByWritingAnything(Update update) {
        StringBuilder searchBy = new StringBuilder();
        String[] s = CountryBot.searchBy.split(" ");
        for (int i = 2; i < s.length; i++) {
            searchBy.append(s[i]).append(" ");
        }

        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText("\uD83D\uDD0D Write " + searchBy.toString().toLowerCase() + "of country");
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_SEARCH));
        return sendMessage;
    }

    @SneakyThrows
    @Override
    public SendMessage searchFromInputSend(Update update) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage sendMessage = new SendMessage()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.RESULT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }
        boolean hasLocation = CountryBot.hasLocation;

        try {
            Response[] countries = RestCountriesUtil.getCountries(CountryBot.currentSearch);

            List<InlineKeyboardButton> row = new ArrayList<>();
            for (Response country : countries) {
                row.add(new InlineKeyboardButton(country.getName()).setCallbackData(country.getAlpha2Code()));
                rowList.add(row);
                row = new ArrayList<>();
            }
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(hasLocation?Constant.SEARCH_WITH_LOCATION:Constant.BACK_TO_SEARCH));
            rowList.add(row);

            return sendMessage;
        } catch (Exception e) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(hasLocation?Constant.SEARCH_WITH_LOCATION:Constant.BACK_TO_SEARCH));
            rowList.add(row);
            sendMessage.setText(Constant.NOT_FOUND);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }

    }

    @Override
    public EditMessageText searchFromInputEdit(Update update) {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.RESULT);
        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }
        boolean hasLocation = CountryBot.hasLocation;
        try {
            Response[] countries = RestCountriesUtil.getCountries(CountryBot.currentSearch);

            List<InlineKeyboardButton> row = new ArrayList<>();
            for (Response country : countries) {
                row.add(new InlineKeyboardButton(country.getName()).setCallbackData(country.getAlpha2Code()));
                rowList.add(row);
                row = new ArrayList<>();
            }
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(hasLocation?Constant.SEARCH_WITH_LOCATION:Constant.BACK_TO_SEARCH));
            rowList.add(row);

            return sendMessage;
        } catch (Exception e) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(hasLocation?Constant.SEARCH_WITH_LOCATION:Constant.BACK_TO_SEARCH));
            rowList.add(row);
            sendMessage.setText(Constant.NOT_FOUND);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }
    }

    @Override
    public EditMessageText setDataOfCountry(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.CURRENCY).setCallbackData(Constant.CURRENCY));
        row.add(new InlineKeyboardButton(Constant.LANGUAGE).setCallbackData(Constant.LANGUAGE));
        row.add(new InlineKeyboardButton(Constant.FLAG).setCallbackData(Constant.FLAG));
        row1.add(new InlineKeyboardButton(Constant.BORDERS).setCallbackData(Constant.BORDERS));
        row1.add(new InlineKeyboardButton(Constant.TIME_ZONES).setCallbackData(Constant.TIME_ZONES));
        row2.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_LIST_OF_COUNTRIES));
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(row);
        rowList.add(row1);
        rowList.add(row2);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        EditMessageText sendMessage = new EditMessageText()
                .setChatId(update.getCallbackQuery().getMessage().getChatId())
                .setReplyMarkup(inlineKeyboardMarkup)
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        try {
            Response country = RestCountriesUtil.getCountryByAlpha2Code(CountryBot.currentCountry);

            String stringBuilder = "\uD83C\uDFD9 Country          ➖  " + country.getName() + "\n" +
                    "\uD83C\uDFE2 Native name ➖  " + country.getNativeName() + "\n" +
                    "\uD83C\uDF09 Capital             ➖  " + country.getCapital() + "\n" +
                    "\uD83C\uDF04 Region             ➖  " + country.getRegion() + "\n" +
                    "\uD83C\uDF01 SubRegion     ➖  " + country.getSubregion() + "\n" +
                    "\uD83E\uDDCD Humans         ➖  " + country.getDemonym() + "\n" +
                    "\uD83D\uDC6B Population    ➖  " + country.getPopulation() + "\n" +
                    "\uD83D\uDDFA Location        ➖  " + country.getLatlng().get(0) + "," + country.getLatlng().get(1) + "\n" +
                    "\uD83D\uDDFE Area                 ➖  " + country.getArea() + " km^2" + "\n" +
                    "\uD83D\uDCF1 Calling code  ➖  " + country.getCallingCodes() + "\n";
            sendMessage.setText(stringBuilder);

            return sendMessage;
        } catch (Exception e) {
            sendMessage.setText(Constant.NOT_FOUND);
            return sendMessage;
        }
    }

    @Override
    public EditMessageText setCurrencyOfCountry(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);


        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup);

        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        try {
            Response country = RestCountriesUtil.getCountryByAlpha2Code(CountryBot.currentCountry);
            StringBuilder stringBuilder = new StringBuilder();

            for (CurrenciesItem currency : country.getCurrencies()) {
                stringBuilder
                        .append("\uD83D\uDCB0 Currency ➖  ").append(currency.getName()).append("\n")
                        .append("\uD83E\uDDFE Code         ➖  ").append(currency.getCode()).append("\n")
                        .append("\uD83D\uDCB2 Symbol    ➖  ").append(currency.getSymbol() != null ? currency.getSymbol().toString() : "no").append("\n")
                        .append("➖➖➖➖➖➖➖➖\n");
            }
            sendMessage.setText(stringBuilder.toString());

            return sendMessage;
        } catch (Exception e) {
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
            sendMessage.setText(Constant.NOT_FOUND);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }
    }

    @Override
    public EditMessageText setLanguageOfCountry(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);


        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup);

        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        try {
            Response country = RestCountriesUtil.getCountryByAlpha2Code(CountryBot.currentCountry);
            StringBuilder stringBuilder = new StringBuilder();
            for (LanguagesItem language : country.getLanguages()) {
                stringBuilder
                        .append("\uD83D\uDCDC Language ➖  ").append(language.getName()).append("\n")
                        .append("\uD83D\uDCF0 Name        ➖  ").append(language.getNativeName()).append("\n")
                        .append("\uD83E\uDDFE Code          ➖  ").append(language.getIso6391()).append("\n\n")
                        .append("➖➖➖➖➖➖➖➖\n\n");
            }
            sendMessage.setText(stringBuilder.toString());

            return sendMessage;
        } catch (Exception e) {
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
            sendMessage.setText(Constant.NOT_FOUND);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }
    }

    @Override
    public EditMessageText setBordersOfCountry(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);


        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup)
                .setText(Constant.RESULT);

        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }

        try {
            Response country = RestCountriesUtil.getCountryByAlpha2Code(CountryBot.currentCountry);
            for (String border : country.getBorders()) {
                Response countryByBorder = RestCountriesUtil.getCountryByAlpha2Code(border);
                row.add(new InlineKeyboardButton(countryByBorder.getName()).setCallbackData(countryByBorder.getAlpha2Code()));
                rowList.add(row);
                row = new ArrayList<>();
            }
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
            rowList.add(row);
            return sendMessage;
        } catch (Exception e) {
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
            sendMessage.setText(Constant.NOT_FOUND);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }
    }

    @Override
    public EditMessageText setTimeZonesOfCountry(Update update) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);


        EditMessageText sendMessage = new EditMessageText()
                .setReplyMarkup(inlineKeyboardMarkup);

        if (update.hasMessage()) {
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setMessageId(update.getMessage().getMessageId());
        } else {
            sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sendMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
        }
        String[] times=new String[12];
        times[0]="\uD83D\uDD50";
        times[1]="\uD83D\uDD51";
        times[2]="\uD83D\uDD52";
        times[3]="\uD83D\uDD53";
        times[4]="\uD83D\uDD54";
        times[5]="\uD83D\uDD55";
        times[6]="\uD83D\uDD56";
        times[7]="\uD83D\uDD57";
        times[8]="\uD83D\uDD58";
        times[9]="\uD83D\uDD59";
        times[10]="\uD83D\uDD5A";
        times[11]="\uD83D\uDD5B";

        try {
            Response country = RestCountriesUtil.getCountryByAlpha2Code(CountryBot.currentCountry);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < country.getTimezones().size() + 1; i++) {
                stringBuilder
                        .append(times[i-1])
                        .append(" ")
                        .append(country.getTimezones().get(i - 1))
                        .append("\n");

            }
            sendMessage.setText(stringBuilder.toString());

            return sendMessage;
        } catch (Exception e) {
            row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT));
            sendMessage.setText(Constant.NOT_FOUND);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }
    }

    @Override
    public SendDocument setFlagOfCountry(Update update) throws IOException {
        List<InlineKeyboardButton> row = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        row.add(new InlineKeyboardButton(Constant.BACK).setCallbackData(Constant.BACK_TO_DATA_OF_COUNTRY_SEND));
        rowList.add(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendDocument sendDocument = new SendDocument()
                .setReplyMarkup(inlineKeyboardMarkup);

        if (update.hasMessage()) {
            sendDocument.setChatId(update.getMessage().getChatId());
        } else {
            sendDocument.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }

        sendDocument.setDocument("Flag of "+CountryBot.currentCountry,RestCountriesUtil.getFlagOfCountry());
        return sendDocument;
    }

    @Override
    public SendMessage removeKeyboardMarkup(Update update) {
        return new SendMessage()
                .setReplyMarkup(new ReplyKeyboardRemove())
                .setText(Constant.OK)
                .setChatId(update.getMessage().getChatId());
    }

    @Override
    public DeleteMessage deleteTwoPrevMessage(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage()
                .setMessageId(CountryBot.messageId);

        if (update.hasMessage()) {
            deleteMessage.setChatId(update.getMessage().getChatId());
        } else {
            deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }

        return deleteMessage;
    }

    @Override
    public DeleteMessage deletePrevMessage(Update update) {
        DeleteMessage deleteMessage = new DeleteMessage()
                .setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        if (update.hasMessage()) {
            deleteMessage.setChatId(update.getMessage().getChatId());
        } else {
            deleteMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        }

        return deleteMessage;
    }


}
