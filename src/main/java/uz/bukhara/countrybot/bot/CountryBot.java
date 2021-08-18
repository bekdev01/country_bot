package uz.bukhara.countrybot.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.bukhara.countrybot.bot.service.LocationService;
import uz.bukhara.countrybot.bot.service.RestCountriesUtil;
import uz.bukhara.countrybot.bot.service.TelegramBot;
import uz.bukhara.countrybot.bot.util.BotState;
import uz.bukhara.countrybot.bot.util.Constant;

@Component
public class CountryBot extends TelegramLongPollingBot {

    @Value("${botToken}")
    private String botToken;

    @Value("${botUsername}")
    private String botUsername;

    @Autowired
    TelegramBot telegramService;





    String state = "";
    public static String currentCountry="";
    public static String currentSearch="";
    public static Integer messageId=null;
    public static String searchBy = null;
    public static boolean hasLocation=false;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                String text = message.getText();
                if (text.equals(Constant.START)) {
                   state=BotState.MAIN_MENU;
                }else if(text.equals(Constant.BACK)){
                    state=BotState.SEARCH_COUNTRY_SEND;
                    searchBy=null;
                } else if(searchBy!=null){
                   state=BotState.LIST_OF_COUNTRIES_SEND;
                   currentSearch=text;
                }else {
                    state=BotState.MAIN_MENU;
                }
            }
            else if(message.hasLocation()){
                hasLocation=true;
                state=BotState.LIST_OF_COUNTRIES_SEND;
                currentSearch= LocationService.getCountry(message.getLocation().getLatitude(),message.getLocation().getLongitude());
                searchBy=Constant.SEARCH_BY_NAME;
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            switch (data) {
                case Constant.SEARCH_COUNTRY:
                case Constant.BACK_TO_SEARCH_COUNTRY:
                    state = BotState.SEARCH_COUNTRY_EDIT;
                    break;
                case Constant.SET_TEXT:
                case Constant.BACK_TO_SEARCH:
                    state = BotState.SEARCH;
                    break;
                case Constant.BACK_TO_MENU:
                    state = BotState.MENU;
                    break;
                case Constant.SEARCH_BY_NAME:
                    state = BotState.WRITING;
                    searchBy = Constant.SEARCH_BY_NAME;
                    break;
                case Constant.SEARCH_BY_CAPITAL:
                    state = BotState.WRITING;
                    searchBy = Constant.SEARCH_BY_CAPITAL;
                    break;
                case Constant.SEARCH_BY_LANGUAGE:
                    state = BotState.WRITING;
                    searchBy = Constant.SEARCH_BY_LANGUAGE;
                    break;
                case Constant.SEARCH_BY_CURRENCY:
                    state = BotState.WRITING;
                    searchBy = Constant.SEARCH_BY_CURRENCY;
                    break;
                case Constant.SEARCH_BY_REGION:
                    state = BotState.WRITING;
                    searchBy = Constant.SEARCH_BY_REGION;
                    break;
                case Constant.SEARCH_BY_CALLING_CODE:
                    state = BotState.WRITING;
                    searchBy = Constant.SEARCH_BY_CALLING_CODE;
                    break;
                case Constant.BACK_TO_LIST_OF_COUNTRIES:
                    state=BotState.LIST_OF_COUNTRIES_EDIT;
                    break;
                case Constant.CURRENCY:
                    state=BotState.CURRENCY_OF_COUNTRY;
                    break;
                case Constant.LANGUAGE:
                    state=BotState.LANGUAGE_OF_COUNTRY;
                    break;
                case Constant.BORDERS:
                    state=BotState.BORDERS_OF_COUNTRY;
                    break;
                case Constant.TIME_ZONES:
                    state=BotState.TIME_ZONES_OF_COUNTRY;
                    break;
                case Constant.FLAG:
                    state=BotState.FLAG_OF_COUNTRY;
                    break;
                case Constant.SEARCH_WITH_LOCATION:
                    state=BotState.SEARCH_WITH_LOCATION;
                    break;
                case Constant.INFO:
                    state=BotState.INFO;
                    break;
                case Constant.BACK_TO_DATA_OF_COUNTRY_EDIT:
                case Constant.BACK_TO_DATA_OF_COUNTRY_SEND:
                default:
                    try {
                       if(data.equals(Constant.BACK_TO_DATA_OF_COUNTRY_EDIT)){
                           RestCountriesUtil.getCountryByAlpha2Code(currentCountry);
                           state=BotState.DATA_OF_COUNTRY_EDIT;
                       } else if(data.equals(Constant.BACK_TO_DATA_OF_COUNTRY_SEND)){
                            RestCountriesUtil.getCountryByAlpha2Code(currentCountry);
                            state=BotState.DATA_OF_COUNTRY_SEND;
                       } else {
                           RestCountriesUtil.getCountryByAlpha2Code(data);
                           state=BotState.DATA_OF_COUNTRY_EDIT;
                           currentCountry=data;
                       }
                    }catch (Exception e){
                        state=BotState.MENU;
                    }
            }
        }

        switch (state) {
            case BotState.MAIN_MENU:
                execute(telegramService.mainMenuSend(update));
                currentCountry="";
                currentSearch="";
                searchBy=null;
                messageId=null;
                hasLocation=false;
                break;
            case BotState.MENU:
                execute(telegramService.mainMenuEdit(update));
                currentCountry="";
                currentSearch="";
                searchBy=null;
                messageId=null;
                hasLocation=false;
                break;
            case BotState.INFO:
                execute(telegramService.menuInfo(update));
                break;
            case BotState.SEARCH_COUNTRY_EDIT:
                searchBy=null;
                currentSearch=null;
                execute(telegramService.searchCountryEdit(update));
                break;
            case BotState.SEARCH_COUNTRY_SEND:
                searchBy=null;
                currentSearch=null;
                execute(telegramService.removeKeyboardMarkup(update));
                execute(telegramService.searchCountrySend(update));
                break;
            case BotState.SEARCH:
                execute(telegramService.menuSearch(update));
                searchBy=null;
                hasLocation=false;
                break;
            case BotState.WRITING:
                execute(telegramService.searchByWritingAnything(update));
                messageId=update.getCallbackQuery().getMessage().getMessageId();
                break;
            case BotState.LIST_OF_COUNTRIES_SEND:
                currentCountry=null;
                execute(telegramService.removeKeyboardMarkup(update));
                execute(telegramService.searchFromInputSend(update));
                if(messageId!=null)
                    execute(telegramService.deleteTwoPrevMessage(update));
                messageId=null;
                break;
            case BotState.LIST_OF_COUNTRIES_EDIT:
                currentCountry=null;
                execute(telegramService.searchFromInputEdit(update));
                if(messageId!=null)
                    execute(telegramService.deleteTwoPrevMessage(update));
                messageId=null;
                break;
            case BotState.DATA_OF_COUNTRY_EDIT:
                execute(telegramService.setDataOfCountry(update));
                break;
            case BotState.DATA_OF_COUNTRY_SEND:
                execute(telegramService.deletePrevMessage(update));
                break;
            case BotState.CURRENCY_OF_COUNTRY:
                execute(telegramService.setCurrencyOfCountry(update));
                break;
            case BotState.LANGUAGE_OF_COUNTRY:
                execute(telegramService.setLanguageOfCountry(update));
                break;
            case BotState.BORDERS_OF_COUNTRY:
                execute(telegramService.setBordersOfCountry(update));
                break;
            case BotState.TIME_ZONES_OF_COUNTRY:
                execute(telegramService.setTimeZonesOfCountry(update));
                break;
            case BotState.FLAG_OF_COUNTRY:
                execute(telegramService.setFlagOfCountry(update));
                break;
            case BotState.SEARCH_WITH_LOCATION:
                execute(telegramService.deletePrevMessage(update));
                execute(telegramService.searchWithLocation(update));
                break;
            default:break;
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
